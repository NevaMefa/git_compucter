package Econbol;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exokardiographia {
    public static void main(String[] args) {
        package jeconbol3.providers.forms;

import duramen.event.SystemEvent;
import duramen.forms.FormController;
import duramen.gui.Dates;
import duramen.gui.ListChoiceDialog;
import duramen.gui.components.builder.FormBuilder;
import duramen.gui.providers.DefaultFormProvider;
import duramen.persistent. *;
import jeconbol3.controller.AuthController;
import jeconbol3.forms.Jeconbol3FormController;
import jeconbol3.persistent.service.Service;
import org.hibernate.type.DateType;

import javax.swing. *;
import java.util. *;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/
 *@author gdo
                */
        public class UltraSoundProvider extends DefaultFormProvider {

            private Wrap model;
            private Wrap modelComboSegments;
            // результаты формул
            private Double stela, ilp, iolp, imm, fvmrej, uo, fbsimpson, ots, kdi, ksi, mok, sibrej,
                    sidr, iopp, ea1, e1a1, ee1, sdla, k17, k27, k37, k47, maxsize;
            private Integer k13, k23, k33, k43;
            // переменные с формы
            private Integer rost, ves, mrej, volume1, mmvrej, kdrlj, ksr, kdo, kso, zslj, mjp, chss,
                    uodr, volume2, pike, pika, pike1, pika1, gradtp, ppp, k12, k22, k32, k42;
            private Double k16, k26, k36, k46;

            @Override
            public void init() {
                super.init();

                model = getStorage().getModel("us");
                if (model == null) {
                    model = new WrapAdapter();
                    getStorage().register("us", model);
                }
                // Значения в выпадающих списках
                Classifier classifier = Util.load(Classifier.class, "kinez");
                List<Wrap> iValues = getStorage().request(classifier, null, "", null);
                modelComboSegments = getStorage().getModel("modelOfCombos");
                if (modelComboSegments == null) {
                    modelComboSegments = new WrapAdapter();
                    getStorage().register("modelOfCombos", modelComboSegments);
                }
                modelComboSegments.setProperty("itemsClassifier", iValues);

                final Service currentService = (Service) getStorage().getModel("service");
                // Результаты предыдущих обследований
                if (currentService != null && !AuthController.havingAuth(currentService.getId()) &&
                        (model.getProperty("visual") == null || currentService.getRezults().isEmpty())) {
                    copyPrevResults(currentService);
                }

                // Установить значение Нормокинез для всех сегментов только
                // при отсутствии параметра "визуализация" (для новых результатов)
                // Установка значений после в отдельном потоке после завершения
                // инициализации формы для сохранения параметров комбобоксов по-умолчанию
                if (model.getProperty("visual") == null) {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(() -> {
                        try {
                            Thread.sleep(840);
                        } catch (InterruptedException e) {
                            duramen.persistent.Util.logException(e);
                        }
                        setComboSegmentNormo(false);
                    });
                    executorService.shutdown();
                }
            }

            @Override
            public void preVisible(boolean visible) {
                Wrap form576Model = registerIfnonExist("form576Model");
                if (visible && form576Model.getProperty("cmbEquip") == null) {
                    List<Wrap> equips = getStorage().getListModel("equips");
                    if (equips != null && equips.size() == 1) {
                        form576Model.setProperty("cmbEquip", equips.get(0));
                        refresh("cmbEquip");
                    }
                }
            }

            @Override
            public void onEvent(SystemEvent e) {
                super.onEvent(e);

                if (e.is("focusLost") && Optional.ofNullable(model)
                        .map(w -> w.getProperty(e.getSrc()))
                        .isPresent()) {
                    SwingUtilities.invokeLater(this::calc);
                } else if (!"occured".equals(e.event) && e.is("value")) {
                    if (e.getSrc().startsWith("AREACMB_")) {    // Комбобоксы управляющие областями на схеме
                        modelComboSegments.setProperty(e.getSrc(), e.value);
                        renderer.refresh("sketch_crd");
                    }
                } else if (e.is("clicked") && "btnClear".equals(e.getSrc())) {
                    // очистка комбобоксов сегментов изображения
                    setComboSegmentNormo(true);

                    // Очистка всех остальных полей
                    model = new WrapAdapter();
                    getStorage().register("us", model);
                    renderer.refresh();
                }

                if (e.isValue("diam2")) {
                    Optional.ofNullable(getStorage().getModel("us").getProperty("diam2"))
                            .ifPresent(prop -> {
                                double d = Double.parseDouble(String.valueOf(prop));
                                getStorage()
                                        .getModel("us")
                                        .setProperty("DavlPP", d >= 20 ? 10 : 5);
                                refresh("DavlPP");
                            });
                }

                if (e.isValue("maxsize1")) { // Непонятное, пока приглушено
                    String value = Optional
                            .ofNullable(getStorage().getModel("us").getProperty("maxsize"))
                            .map(prop -> {
                                double d = Double.parseDouble(String.valueOf(prop));
                                if (d > 0) {
                                    double dlProperty = Optional
                                            .ofNullable(getStorage().getModel("us").getProperty("DavlPP"))
                                            .map(String::valueOf)
                                            .map(Double::parseDouble)
                                            .orElse((double) 0);
                                    Double result;
                                    if (dlProperty <= 0) {
                                        result = 4 * (Math.sqrt(d));
                                    } else {
                                        result = dlProperty + 4 * (Math.sqrt(d));
                                    }
                                    return String.valueOf(result);
                                }
                                return "";
                            })
                            .orElse("");
                    getStorage().getModel("us").setProperty("SistDLA", value);
                    refresh("SistDLA");
                }

                if (e.isValue("DavlPP")) {
                    model = getStorage().getModel("us");
                    double davlPP = Optional.ofNullable((Double) model.getProperty("DavlPP")).orElse(0.);
                    int gradtp = Optional.ofNullable((Integer) model.getProperty("gradtp")).orElse(0);
                    if (gradtp != 0 && (davlPP == 5. || davlPP == 10.)) {
                        model.setProperty("SistDLA", gradtp + davlPP);
                        refresh("SistDLA");
                    }
                }

                if (e.isValue("kdrlj") || e.isValue("mjp") || e.isValue("zslj")) {
                    calculateMmvrej();
                }

                if (e.isValue("stela")) {
                    calculateImm();
                }
            }

            private void calculateImm() {
                Double st = getValueFromModelUs("stela");
                if (st == 0d) {
                    getStorage().getModel("us").setProperty("imm", null);
                    refresh("imm");
                } else {
                    Double mmvrej = getValueFromModelUs("mmvrej");
                    getStorage().getModel("us").setProperty("imm", (int) Math.round(mmvrej / st));
                    refresh("imm");
                }
            }

            private void calculateMmvrej() {
                Double d5Value = getValueFromModelUs("kdrlj");
                Double d7Value = getValueFromModelUs("zslj");
                Double d8Value = getValueFromModelUs("mjp");

                double result = (0.8d * (1.04d *
                        (Math.pow(d5Value + d7Value + d8Value, 3) -
                                Math.pow(d5Value, 3)) + 0.6)) / 1000d;

                long round = Math.round(result);
                getStorage().getModel("us").setProperty("mmvrej", (int) round);
                refresh("mmvrej");
            }

            private Double getValueFromModelUs(String index) {
                try {
                    return Optional
                            .ofNullable(getStorage().getModel("us").getProperty(index))
                            .map(String::valueOf)
                            .map(Double::parseDouble)
                            .orElse(0d);
                } catch (NumberFormatException e) {
                    return 0d;
                }
            }

            private void checkVars() {
                stela = null;
                ilp = null;
                iolp = null;
                imm = null;
                fvmrej = null;
                uo = null;
                fbsimpson = null;
                ots = null;
                kdi = null;
                ksi = null;
                mok = null;
                sibrej = null;
                sidr = null;
                iopp = null;
                ea1 = null;
                e1a1 = null;
                ee1 = null;
                sdla = null;
                k17 = null;
                k27 = null;
                k37 = null;
                k47 = null;
                k13 = null;
                k23 = null;
                k33 = null;
                k43 = null;

                ves = (Integer) model.getProperty("ves");
                rost = (Integer) model.getProperty("rost");
                mrej = (Integer) model.getProperty("mrej");
                volume1 = (Integer) model.getProperty("volume1");
                mmvrej = (Integer) model.getProperty("mmvrej");
                kdrlj = (Integer) model.getProperty("kdrlj");
                ksr = (Integer) model.getProperty("ksr");
                kdo = (Integer) model.getProperty("kdo");
                kso = (Integer) model.getProperty("kso");
                zslj = (Integer) model.getProperty("zslj");
                mjp = (Integer) model.getProperty("mjp");
                chss = (Integer) model.getProperty("chss");
                uodr = (Integer) model.getProperty("uodr");
                volume2 = (Integer) model.getProperty("volume2");
                pike = (Integer) model.getProperty("pike");
                pika = (Integer) model.getProperty("pika");
                pike1 = (Integer) model.getProperty("pike1");
                pika1 = (Integer) model.getProperty("pika1");
                gradtp = (Integer) model.getProperty("gradtp");
                maxsize = (Double) model.getProperty("maxsize");
                ppp = (Integer) model.getProperty("ppp");
                k16 = (Double) model.getProperty("k16");
                k26 = (Double) model.getProperty("k26");
                k36 = (Double) model.getProperty("k36");
                k46 = (Double) model.getProperty("k46");
                k12 = (Integer) model.getProperty("k12");
                k22 = (Integer) model.getProperty("k22");
                k32 = (Integer) model.getProperty("k32");
                k42 = (Integer) model.getProperty("k42");
            }

            // основной обсчет данных
            private void calc() {
                // пересчитываем
                checkVars();

                // 1. площадь поверхности тела
                if (checkZero(ves) && checkZero(rost)) {
                    stela = round(0.007184d * Math.pow(ves, 0.425d) * Math.pow(rost, 0.725d), 2);
                }
                model.setProperty("stela", stela);

                // 2. илп
                if (checkZero(stela) && mrej != null) {
                    ilp = round(mrej / stela, 1);
                }
                model.setProperty("ilp", ilp);

                // 3. иолп
                if (checkZero(stela) && volume1 != null) {
                    iolp = round(volume1 / stela, 1);
                }
                model.setProperty("iolp", iolp);

                // 4. имм
                if (checkZero(stela) && mmvrej != null) {
                    imm = round(mmvrej / stela, 1);
                }
                model.setProperty("imm", imm);

                // 5. фв(м-реж)
                if (ksr != null && checkZero(kdrlj)) {
                    fvmrej = round(((kdrlj - ksr) / kdrlj.doubleValue()) * 100d, 0);
                }
                model.setProperty("fvmrej", fvmrej);

                // 6. уо
                if (kdo != null && kso != null) {
                    uo = round((kdo - kso) + 0d, 0);
                }
                model.setProperty("uo", uo);

                // 7. ФВ Simpson
                if (checkZero(kdo) && uo != null) {
                    fbsimpson = round((uo / kdo) * 100, 0);
                }
                model.setProperty("fbsimpson", fbsimpson);

                // 8. ОТС
                if (checkZero(kdrlj) && mjp != null && zslj != null) {
                    ots = round((zslj + mjp) / kdrlj.doubleValue() + 0d, 2);
                }
                model.setProperty("ots", ots);
                // 9. КДИ
                if (kdo != null && checkZero(stela)) {
                    kdi = round(kdo / stela, 0);
                }
                model.setProperty("kdi", kdi);

                // 10. КСИ
                if (kso != null && checkZero(stela)) {
                    ksi = round(kso / stela, 0);
                }
                model.setProperty("ksi", ksi);

                // 11. МОК
                if (uo != null && chss != null) {
                    mok = round((uo / 1000) * chss, 1);
                }
                model.setProperty("mok", mok);

                // 12. СИ (В-реж)
                if (mok != null && checkZero(stela)) {
                    sibrej = round((mok / stela), 1);
                }
                model.setProperty("sibrej", sibrej);

                // 13. СИ (Д-р)
                if (uodr != null && chss != null && checkZero(stela)) {
                    sidr = round(((uodr / 1000) * chss) / stela, 1);
                }
                model.setProperty("sidr", sidr);

                // 14. ИОПП
                if (volume2 != null && checkZero(stela)) {
                    iopp = round((volume2 / stela), 0);
                }
                model.setProperty("iopp", iopp);

                // 15. E/A
                if (pike != null && checkZero(pika)) {
                    ea1 = round((pike.doubleValue() / pika.doubleValue()) + 0d, 2);
                }
                model.setProperty("ea1", ea1);

                // 16. E1/A1
                if (pike1 != null && checkZero(pika1)) {
                    e1a1 = round((pike1 / pika1.doubleValue()), 0);
                }
                model.setProperty("e1a1", e1a1);

                // 17. E/E1
                if (pike != null && checkZero(pike1)) {
                    ee1 = round((pike / pike1.doubleValue()), 0);
                }
                model.setProperty("ee1", ee1);

                // 18. СДЛА
                if (gradtp != null && ppp != null) {
                    sdla = round((gradtp + ppp) + 0d, 0);
                }
                model.setProperty("sdla", sdla);

                // gradtp
                if (maxsize != null) {
                    gradtp = Math.toIntExact(Math.round(maxsize * maxsize * 0.0004));
                }
                model.setProperty("gradtp", gradtp);

                //   КЛАПАНА

                // k17
                if (stela != null && k16 != null) {
                    k17 = round((k16 / stela), 1);
                }
                model.setProperty("k17", k17);

                // k27
                if (stela != null && k26 != null) {
                    k27 = round((k26 / stela), 1);
                }
                model.setProperty("k27", k27);

                // k37
                if (stela != null && k36 != null) {
                    k37 = round((k36 / stela), 1);
                }
                model.setProperty("k37", k37);

                // k47
                if (stela != null && k46 != null) {
                    k47 = round((k46 / stela), 1);
                }
                model.setProperty("k47", k47);

                // k13
                if (k12 != null) {
                    k13 = Math.toIntExact(Math.round(k12 * k12 * 0.0004));
                }
                model.setProperty("k13", k13);

                // k23
                if (k22 != null) {
                    k23 = Math.toIntExact(Math.round(k22 * k22 * 0.0004));
                }
                model.setProperty("k23", k23);

                // k33
                if (k32 != null) {
                    k33 = Math.toIntExact(Math.round(k32 * k32 * 0.0004));
                }
                model.setProperty("k33", k33);

                // k43
                if (k42 != null) {
                    k43 = Math.toIntExact(Math.round(k42 * k42 * 0.0004));
                }
                model.setProperty("k43", k43);

                Arrays.asList("stela", "ilp", "iolp", "imm", "fvmrej", "uo", "fbsimpson", "ots", "kdi", "ksi", "mok", "sibrej",
                                "sidr", "iopp", "ea1", "e1a1", "ee1", "sdla", "gradtp",
                                "k17", "k27", "k37", "k47", "k13", "k23", "k33", "k43")
                        .forEach(this::refresh);
            }

            private boolean checkZero(Number n) {
                return (n != null && !n.equals(0));
            }

            @Override
            public boolean isEditable(String src, Wrap wrap, String property) {
                return true;
            }

            private Double round(Double f, Integer s) {
                return ((f != null && s != null) ? Double.valueOf(String.valueOf(Math.round(f * Math.pow(10, s)) / Math.pow(10, s))) : null);
            }
            /
                    *
            Установка значений
            комбобоксов в
            Номрокинез
     *
             *
            @param
            isManual true-
            когда запущена

            вручную пользователем(явная перезапись имеющихся not-null значений)
     *false-
            при инициализации

            формы автоматически(установка значений только в null комбобоксах)
     */

            private void setComboSegmentNormo(boolean isManual) {
                List<Wrap> iValues = null;
                if (modelComboSegments != null) {
                    iValues = (List<Wrap>) modelComboSegments.getProperty("itemsClassifier");
                }
                if (iValues == null)
                    return;
                GeneralClassifierItem normoVal = (GeneralClassifierItem) iValues.stream()
                        .filter(wrap -> ((GeneralClassifierItem) wrap).getIndex().equals("N"))
                        .findFirst().orElse(null);
                Set<String> properties = modelComboSegments.getProperties().keySet();
                SwingUtilities.invokeLater(() -> {
                    properties.stream().filter(k -> k.startsWith("AREACMB_"))
                            .filter(k -> isManual || modelComboSegments.getProperty(k) == null)
                            .forEach(k -> modelComboSegments.setProperty(k, normoVal));

                    properties.stream().filter(k -> k.startsWith("AREACMB_")).forEach(k -> refresh(k));
                    refresh("sketch");
                });
            }

            /**
             * Копирование результатов из предыдущего обследования
             * TODO выбрать услугу из которой брать результаты?
             */
            private void copyPrevResults(Service currentService) {
                if (currentService != null) {
                    List<Object[]> prevServices = Util.currentSession().createSQLQuery(
                                    "SELECT {s.*}, d.date AS dt FROM " +
                                            " se_services AS s " +
                                            " JOIN se_dests AS d ON s.id=d.service_id " +
                                            " JOIN se_executions AS e ON s.id=e.service_id " +
                                            " WHERE d.entry_id=:entry AND s.patient_id=:patient " +
                                            " AND s.id <> :cur_service_id " +
                                            " AND s.removed = FALSE " +
                                            " AND e.finished = TRUE AND e.clinic_id=:clinic_id " +
//                            " AND e.execution_status=:ex_status " +
                                            " ORDER BY d.date DESC ")
                            .addEntity("s", Service.class)
                            .addScalar("dt", DateType.INSTANCE)
                            .setLong("entry", currentService.getServiceEntry(Util.user.getClinic()).getId())
                            .setLong("cur_service_id", currentService.getId())
                            .setLong("clinic_id", Util.user.getClinic().getId())
//                                    .setParameter("ex_status", ExecutionStatus.FINISHED_WITH_AUTH.ordinal())
                            .setLong("patient", currentService.getPatient().getId())
                            .list();
                    if (!prevServices.isEmpty()) {
                        Service prevService = null;
                        String title = "Загрузить результаты предыдущего обследования?";
                        if (prevServices.size() == 1) {
                            if (question(title, 0))
                                prevService = (Service) prevServices.get(0)[0];
                        } else {
                            ListChoiceDialog<Object[]> dialog = new ListChoiceDialog<Object[]>(title,
                                    prevServices, null, getFrame()) {
                                @Override
                                protected String render(Object[] object) {
                                    StringBuilder sb = new StringBuilder("<html>Результаты обследования");
                                    if (object[1] != null && object[1] instanceof Date) {
                                        Date d = (Date) object[1];
                                        sb.append(" от ")
                                                .append(Dates.date2str4rus(d));
                                    }
                                    sb.append("<br><font color='#6060CC' size='-2'>Копировать</font></html>");
                                    return sb.toString();
                                }
                            };
                            dialog.setVisible(true);
                            Object[] item = dialog.getItem();
                            if (item != null)
                                prevService = (Service) item[0];
                        }

                        if (prevService != null) {
                            getFormRenderer().getApplicationContext().storage.register("service", prevService);
                            FormController tempController = new Jeconbol3FormController((FormBuilder) this.getFormRenderer());
                            tempController.init();
                            tempController = null;
                            // Вернуть текущую услугу в модель для сохранения предыдущих результатов в новой услуге.
                            getFormRenderer().getApplicationContext().storage.register("service", currentService);
                        }
                    }
                }
            }
        }
    }

}
}
