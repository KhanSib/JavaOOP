package ru.academits.khanov.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> fileList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream("input.txt")) {
            Scanner scanner = new Scanner(fileInputStream);

            while (scanner.hasNextLine()) {
                fileList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: отсутствует файл для считывания строк");
        }

        System.out.println("Список строк из файла: " + fileList);

        ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (int i = 0; i < integersList.size(); i++) {
            Integer integer = integersList.get(i);

            if (integer % 2 == 0) {
                integersList.remove(integer);
            }
        }

        System.out.println("Список без четных чисел: " + integersList);

        ArrayList<Integer> listWithDuplicates = new ArrayList<>(Arrays.asList(4, 2, 1, 2, 3, 4, 5, 4, 2, 1));
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>();

        for (Integer integer : listWithDuplicates) {
            if (!listWithoutDuplicates.contains(integer)) {
                listWithoutDuplicates.add(integer);
            }
        }

        System.out.println("Список с дубликатами чисел: " + listWithDuplicates);
        System.out.println("Список без дубликатов: " + listWithoutDuplicates);
    }
}