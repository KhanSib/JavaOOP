package ru.academits.khanov.hashtable.main;

import ru.academits.khanov.hashtable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10);

        hashTable.add(2);
        hashTable.add(2);
        hashTable.add(2);

    }
}
