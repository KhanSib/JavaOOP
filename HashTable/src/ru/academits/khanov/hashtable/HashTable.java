package ru.academits.khanov.hashtable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
    Object[] linkedLists;

    public HashTable(int size) {
        linkedLists = new Object[size];
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
    public boolean add(Object o) {
        if (o == null) {
            throw new NullPointerException("Элемент для добавления не может быть null");
        }

        int index = o.hashCode() % size();

        if (linkedLists[index] == null) {
            linkedLists[index] = new LinkedList<>();
        }

        LinkedList<T> linkedList = (LinkedList<T>) linkedLists[index];

        linkedList.add((T) o);

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
