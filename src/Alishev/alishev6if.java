package Alishev;

/**
 * if проверка условий. Если сопадает то
 * выполнение инструкции
 * если условие не совпало, то либо что-то, либо ничего
 * если переменная подходит к 2 значеним, то выполнит программа первое условие
 * тк она работает последовательно
 */



public class alishev6if {
    public static void main(String[] args) {
        int myInt = 21;
        //если
        if (myInt<10){
            System.out.println("Да,верно ");
            // или
        }else if (myInt>20){
            System.out.println("Нет, не верно ");
            // иначе
        }else {
            System.out.println("Ни один из предыдущих случаев");
        }
    }
}
