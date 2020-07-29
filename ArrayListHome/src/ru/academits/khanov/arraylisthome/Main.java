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

            while (scanner.hasNext()) {
                list1.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i) % 2 == 0) {
                list2.remove(i);
            }
        }

        System.out.println(list2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(4, 2, 1, 2, 3, 4, 5, 4, 2, 1));
        ArrayList<Integer> list4 = new ArrayList<>(list3);

        for (int element: list3) {
            if (list2.get(i) % 2 == 0) {
                list2.remove(i);
            }
        }

        System.out.println(list2);
    }
}
