package ru.academits.khanov.hashtable.main;

import ru.academits.khanov.hashtable.HashTable;

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
        System.out.println(hashTable);

        //for (Integer integer : hashTable) {
        //    System.out.println(integer);
        // }

        System.out.println(hashTable.remove(2));
        System.out.println(hashTable);

        System.out.println(hashTable.removeAll(Collections.singletonList(2)));
        System.out.println(hashTable);
        System.out.println(hashTable.size());
        hashTable.clear();
        System.out.println(hashTable);
    }
}