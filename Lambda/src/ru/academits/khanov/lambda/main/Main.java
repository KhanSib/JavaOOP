package ru.academits.khanov.lambda.main;

import ru.academits.khanov.lambda.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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

        System.out.print("Уникальные имена: " + persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", ", "", ".")));

        List<Person> peoplesYounger18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Люди младше 18 лет: " + peoplesYounger18);

        OptionalDouble ageAverage = peoplesYounger18
                .stream()
                .mapToInt(Person::getAge)
                .average();

        System.out.println("Средний возраст людей младше 18 лет: " + ageAverage);


        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println("Средний возраст по именам: " + averageAgeByName);

        List<Person> peoplesWithAgeBetween20And45 = persons.stream()
                .filter(x -> x.getAge() > 20 && x.getAge() < 45)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());

        System.out.println("Список людей с возрастом от 20 до 45 лет: " + peoplesWithAgeBetween20And45);

        Scanner scanner = new Scanner(System.in);
/*
        System.out.println("Сколько элементов необходимо вычислить: ");
        int count = scanner.nextInt();

        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt).limit(count);
        squareRoots.forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
                .limit(count)
                .map(x -> x[0])
                .forEach(System.out::println);*/
    }
}
