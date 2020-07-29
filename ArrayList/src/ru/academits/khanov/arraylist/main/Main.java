package ru.academits.khanov.arraylist.main;

import ru.academits.khanov.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*ArrayList<String> list = new ArrayList<>(new String[]{
                "строка 1", "строка 2", "строка 3", "строка 4", "строка 5"
        });*/
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5});


       /* for (String text : list) {
            System.out.println(text);
        }*/

        System.out.println(Arrays.toString(list.toArray(new Long[10])));

        list.clear();

        System.out.println(list);
    }
}
