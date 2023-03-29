package Alishev;

/**
 * continue после этого оператора ничего не выполняется
 * круг будет выполнятся пока не вполнится условие
 */
public class alishev9continue {
    public static void main(String[] args) {
        for (int i = 0; i <= 15; i++){
            //% значит подели на эточисло и посмотри остаток
            // остаток от деления
            if (i%2==0){
                // в данном случае будет работать только с четным числом
                continue;
            }
            System.out.println("Это нечетное число "+ i);
        }
    }
}
