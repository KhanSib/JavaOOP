package ru.cft.test.khanov;

import java.io.*;
import java.util.*;

public class FileMergeSorting<T> {
    List<File<T>> files;
    String outputFile;
    private final Comparator<T> comparator;

    public FileMergeSorting(String[] inputFiles, String outputFile) throws FileNotFoundException {
        files = new ArrayList<>();

        for (String inputFile : inputFiles) {
            files.add(new File<>(inputFile));
        }

        this.outputFile = outputFile;
        comparator = (Comparator<T>) Comparator.nullsFirst(Comparator.naturalOrder());
    }

    public FileMergeSorting(String[] inputFiles, String outputFile, Comparator<T> comparator) throws FileNotFoundException {
        files = new ArrayList<>();

        for (String inputFile : inputFiles) {
            files.add(new File<>(inputFile));
        }

        this.outputFile = outputFile;
        this.comparator = comparator;
    }

    public void mergingFiles() {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            while (!files.isEmpty()) {
                T currentElement = files.get(0).getElement();
                int currentIndex = 0;

                for (int i = 0; i < files.size(); i++) {
                    if (comparator.compare(currentElement, files.get(i).getElement()) >= 0) {
                        currentElement = files.get(i).getElement();
                        currentIndex = i;
                    }
                }
                //TODO: remove printLn
                System.out.println(currentElement);
                writer.println(currentElement);


                if (files.get(currentIndex).getScanner().hasNext()) {
                    files.get(currentIndex).setElement((T) files.get(currentIndex).getScanner().nextLine());
                } else {
                    files.remove(currentIndex);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

