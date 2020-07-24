package ru.academits.khanov.arraylist.main;

import ru.academits.khanov.arraylist.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(new String[]{
                "строка 1", "строка 2", "строка 3", "строка 4", "строка 5"
        });

        for (String text : list) {
            System.out.println(text);
        }
    }
}
