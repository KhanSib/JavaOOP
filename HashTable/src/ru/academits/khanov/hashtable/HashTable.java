package ru.academits.khanov.hashtable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
    private LinkedList<T>[] linkedLists;

    public HashTable(int size) {
        LinkedList<Object>[] linkedLists = new LinkedList[size];
        System.out.println(linkedLists[0].toString());
        this.linkedLists = (LinkedList<T>[]) linkedLists;
    }

    @Override
    public int size() {
        return linkedLists.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(T data) {
        if (data == null) {
            throw new NullPointerException("Элемент для добавления не может быть null");
        }

        int index = data.hashCode() % size();
        linkedLists[index].add(data);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
