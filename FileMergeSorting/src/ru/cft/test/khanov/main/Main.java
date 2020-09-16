package ru.cft.test.khanov.main;

import ru.cft.test.khanov.ElementsType;
import ru.cft.test.khanov.FileMergeSorting;
import ru.cft.test.khanov.OrderBy;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    private static void help() {
        System.out.println("Первый аргумент: режим сортировки -a по возрастанию," +
                " -d по убыванию, необязательный, по умолчанию сортируется по возрастанию;");
        System.out.println("Второй аргумент: тип данных -s текстовый, -i целочисленный, обязательный;");
        System.out.println("Третий аргумент: имя выходного файла, обязательное;");
        System.out.println("Последующие аргументы: имена входных файлов, не менее одного;");
        System.out.println("Пример: main -d -s out.txt in1.txt in2.txt");
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            help();
            throw new IllegalArgumentException("Не достаточно аргументов командной строки.");
        }

        ElementsType elementsType = ElementsType.INTEGER;
        OrderBy orderBy = OrderBy.ASC;

        String args0 = args[0];

        boolean hasOrderByArgument = true;

        if (args0.equals("-a") || args0.equals("-d")) {
            if (args0.equals("-d")) {
                orderBy = OrderBy.DESC;
            }
        } else {
            hasOrderByArgument = false;
        }

        String args1 = args[1];

        if (hasOrderByArgument) {
            if (args1.equals("-s") || args1.equals("-i")) {
                if (args1.equals("-s")) {
                    elementsType = ElementsType.STRING;
                }
            } else {
                help();
                throw new IllegalArgumentException("Не валидные аргументы командной строки.");
            }
        } else {
            if (args0.equals("-s") || args0.equals("i")) {
                if (args0.equals("-s")) {
                    elementsType = ElementsType.STRING;
                }
            } else {
                help();
                throw new IllegalArgumentException("Не валидные аргументы командной строки.");
            }
        }

        String outFile;
        String[] files = new String[]{};

        if (hasOrderByArgument) {
            outFile = args[2];
            files = Arrays.copyOf(files, args.length - 3);
            System.arraycopy(args, 3, files, 0, args.length - 3);
        } else {
            outFile = args[1];
            files = Arrays.copyOf(files, args.length - 2);
            System.arraycopy(args, 2, files, 0, args.length - 2);
        }

        try {
            FileMergeSorting<String> fileMergeSorting = new FileMergeSorting<>(files, outFile, elementsType);
            fileMergeSorting.mergingFiles(orderBy);
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
