package Alishev;

/**
 * операторы, которые используютс с циклами
 * break для выхода из цикла
 * слово true в данном случае будет крутить цикл бесконечно
 */
public class alishev9break {
    public static void main(String[] args) {
        int i = 0;
        while (true){
            if (i >= 15){
                break;
            }
            System.out.println(i);
            i++;
        }
        System.out.println("Мы вышли из цикла");
    }
}
