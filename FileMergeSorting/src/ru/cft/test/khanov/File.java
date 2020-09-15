package ru.cft.test.khanov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class File<T> {
    private T element;
    private Scanner scanner;

    public File(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        this.scanner = scanner;

        if (scanner.hasNext()) {
            element = (T) scanner.nextLine();
        }
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
