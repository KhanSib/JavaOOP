package ru.academits.khanov.lambda.main;

import ru.academits.khanov.lambda.Person;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Person> persons = new LinkedList<>(Arrays.asList(
                new Person("Ivan", 40),
                new Person("Ivan", 18),
                new Person("Ivan", 17),
                new Person("Ivan", 30),
                new Person("Ivan", 45),
                new Person("Ivan", 50),
                new Person("Oksana", 29),
                new Person("Petr", 30),
                new Person("Eugene", 31),
                new Person("Svetlana", 45),
                new Person("Roman", 50),
                new Person("Mark", 29),
                new Person("Ilya", 30),
                new Person("Vladimir", 31),
                new Person("Uliya", 45)
        ));

        persons.forEach(System.out::println);

    }
}
