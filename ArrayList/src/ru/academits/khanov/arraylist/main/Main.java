package ru.academits.khanov.arraylist.main;

import ru.academits.khanov.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10);

        ArrayList<String> list1 = new ArrayList<>(new String[]{
                "строка 1", "строка 2", "строка 3", "строка 4", "строка 5"
        });

        for (String text : list1) {
            System.out.println(text);
        }

        System.out.println("Удаление элемента \"строка 2\": " + list1.remove("строка 2"));
        System.out.println(list1);

        list1.add(1, "строка 2");
        System.out.println("Добавление элемента \"строка 2\" по индексу 1: " + list1);

        list1.addAll(2, list1);
        System.out.println("Добавление list1 по индексу 2 в list1: " + list1);

        list1.remove(1);
        System.out.println("Удаление элемента по индексу 1 в list1: " + list1);

        ArrayList<String> list2 = new ArrayList<>(new String[]{"строка 1", "строка 2"});
        list1.retainAll(list2);

        System.out.println("Оставляем все элементы list1 которые содержатся в list2: " + list1);

        ArrayList<Integer> list3 = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5});
        ArrayList<Integer> list4 = new ArrayList<>(new Integer[]{1, 2});

        System.out.println("Содержит ли элементы list3 элементы list4: " + list3.containsAll(list4));

        Integer[] array = new Integer[10];

        System.out.println(Arrays.toString(list3.toArray(array)));

        list3.clear();

        System.out.println(list3);
    }
}