package javaClass;

public class isClass {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Poman";
        person1.age = 50;
        //System.out.println("Меня зовут " + person1.name + "," + "мне " + person1.age);
        // вызовем метод speak
        int year1 = person1.calculateYearsTiRetirement();
        System.out.println("Первому человеку до пенсии: " +year1+ "лет");
        Person person2 = new Person();
        person2.name = "BoBa";
        person2.age = 20;
        int year2 = person2.calculateYearsTiRetirement();
        System.out.println("Второму человеку до пенсии: " +year2+ "лет");
        //System.out.println("Меня зовут " + person2.name + "," + "мне " + person2.age);

    }
}

class Person {
    // у класса могут быть:
    // 1. Данные (поля)
    // 2. Действия, которые он может совершать (методы)
    // РАЗБЕРЕМ ПОЛЯ:
    String name;
    int age;

//тип возвращаемого значени метода, должен быть у каждого метода int, void,string

    int calculateYearsTiRetirement(){
        int years = 65 - age;
        return years;
        //после return нет смысла что-то еще писать,  метод закрываетс
        // возращаетс только конкретно указанный тип, другой нельз
    }





    //Действие по выводу метода с представлением
    void speak() {
        System.out.printf(" Меня зовут " + name + ",мне " + age + " лет");
    }

    void sayHello() {
        System.out.printf(" Привет! ");
    }
}