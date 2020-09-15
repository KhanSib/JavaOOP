package ru.cft.test.khanov.main;

import ru.cft.test.khanov.FileMergeSorting;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = new String[]{"in1.txt", "in2.txt","in3.txt" };

        FileMergeSorting<Integer> fileMergeSorting = new FileMergeSorting<>(files, "out.txt");
        fileMergeSorting.mergingFiles();
    }
}
