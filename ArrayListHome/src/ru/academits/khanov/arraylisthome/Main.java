package ru.academits.khanov.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new FileInputStream("input.txt"));

            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: отсутствует файл для считывания строк");
        }

        System.out.println("Список строк из файла: " + list1);

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        list2.removeIf(item -> item % 2 == 0);

        System.out.println("Список без четных чисел: " + list2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(4, 2, 1, 2, 3, 4, 5, 4, 2, 1));
        ArrayList<Integer> list4 = new ArrayList<>();

        for (Integer integer : list3) {
            if (!list4.contains(integer)) {
                list4.add(integer);
            }
        }

        System.out.println("Список с дубликатами чисел: " + list3);
        System.out.println("Список без дубликатов: " + list4);
    }
}