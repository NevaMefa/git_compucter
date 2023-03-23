/**
 * оператор while
 *  в {} телочикла того, что будет выполнт несколько кол-во раз
 *  в ()  условие  - то что будет выполнтьс н-ное кол-во раз
 */


public class alishev4while {
    public static void main(String[] args) {
     // присвоили и инциализироввали переменную
        int value = 0;
        // цикл будет до тех пор пока value  меньше 5
        while (value<5){
            //выводим слово и значение переменной
            System.out.println("Hello " + value);
            // на каждом круге к переменной + 1
            value = value +1;
        }
    }
}
