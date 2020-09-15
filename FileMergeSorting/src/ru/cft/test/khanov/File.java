package ru.cft.test.khanov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class File<T> {
    private final String fileName;
    private T element;
    private Scanner scanner;

    public File(String fileName) throws FileNotFoundException {
        this.fileName = fileName;

      //  try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
        Scanner scanner = new Scanner(new FileInputStream(fileName));
            this.scanner = scanner;

            if (scanner.hasNext()) {
                element = (T) scanner.nextLine();
            }
      /*  } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
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

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
