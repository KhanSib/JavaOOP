package ru.academits.khanov.lambda.main;

import ru.academits.khanov.lambda.Person;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new LinkedList<>(Arrays.asList(
                new Person("Ivan", 40),
                new Person("Ivan", 18),
                new Person("Ivan", 17),
                new Person("Ivan", 15),
                new Person("Ivan", 5),
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

        List<String> uniqNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Уникальные имена: ");
        uniqNames.forEach(s -> System.out.print(s + ", "));

        List<Person> peoplesYounger18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("Люди младше 18 лет: ");
        peoplesYounger18.forEach(s -> System.out.print(s + ", "));

        System.out.println("Средний возраст людей младше 18 лет: " +
                peoplesYounger18
                        .stream()
                        .mapToInt(Person::getAge)
                        .average());

        Map<Object, List<Person>> map = persons.stream().collect(Collectors.groupingBy(Person::getName));
        map.forEach((k, v) -> System.out.print(k + " " + v + ","));
    }
}
