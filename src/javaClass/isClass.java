package javaClass;

public class isClass {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Poman";
        person1.age = 50;
        //System.out.println("Меня зовут " + person1.name + "," + "мне " + person1.age);
        // вызовем метод speak
        person1.sayHello();
        Person person2 = new Person();
        person2.name = "BoBa";
        person2.age = 20;
        person2.speak();
        person2.sayHello();
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

    //Действие по выводу метода с представлением
    void speak() {
        System.out.printf(" Меня зовут " + name + ",мне " + age + " лет");
    }

    void sayHello() {
        System.out.printf(" Привет! ");
    }
}