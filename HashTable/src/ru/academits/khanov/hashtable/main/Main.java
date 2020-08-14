package ru.academits.khanov.hashtable.main;

import ru.academits.khanov.hashtable.HashTable;

import java.util.Arrays;
import java.util.Collections;

public class Main { 
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10);

        hashTable.add(2);
        hashTable.add(2);
        hashTable.add(2);
        hashTable.add(7);
        hashTable.add(8);
        hashTable.add(9);
        hashTable.add(18);
        hashTable.add(19);
        hashTable.add(null);

        System.out.println("Элементы хэш-таблицы, после вставки: " + hashTable);

        System.out.print("Проверка итератора: ");

        for (Integer integer : hashTable) {
            System.out.print(integer + " ");
        }

        System.out.println();

        System.out.println("Удаление 2ки: " + hashTable.remove(2));
        System.out.println("Элементы хэш-таблицы, после удаления 2ки: " + hashTable);

        System.out.println("Удаление одиночной коллекции (2): " + hashTable.removeAll(Collections.singletonList(2)));
        System.out.println("Элементы хэш-таблицы, после удаления одиночной коллекции (2): " + hashTable);

        System.out.println("Размер хэш-таблицы: " + hashTable.size());

        System.out.println("Массив элементов хэш-таблицы: " + Arrays.toString(hashTable.toArray()));

        hashTable.clear();
        System.out.println("Элементы хэш-таблицы, после удаления всех элементов: " + hashTable);
    }
}