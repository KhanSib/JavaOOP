package ru.academits.khanov.hashtable;

import java.lang.reflect.Array;
import java.util.*;

public class HashTable<T> implements Collection<T> {
    private LinkedList<T>[] linkedLists;

    public HashTable(int size) {
        LinkedList<Object>[] linkedLists = new LinkedList[size];
        this.linkedLists = (LinkedList<T>[]) linkedLists;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (LinkedList<T> linkedList : linkedLists) {
            if (linkedList != null) {
                stringBuilder.append(linkedList.toString());
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public int size() {
        int size = 0;

        for (LinkedList<T> linkedList : linkedLists) {
            if (linkedList != null && !linkedList.isEmpty()) {
                size += linkedList.size();
            }
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return linkedLists[Math.abs(o.hashCode() % linkedLists.length)] != null &&
                linkedLists[Math.abs(o.hashCode() % linkedLists.length)].contains(o);
    }

    private class HashTableIterator implements Iterator<T> {
        private int elementsCount = 0;
        private int linkedListIndex = 0;
        private int elementIndex = -1;

        @Override
        public boolean hasNext() {
            return elementsCount + 1 <= size();
        }

        @Override
        public T next() {
            if (elementsCount + 1 > size()) {
                throw new NoSuchElementException("Отсутствует следующий элемент");
            }

            T value = null;

            while (linkedListIndex < linkedLists.length &&
                    (linkedLists[linkedListIndex] == null || linkedLists[linkedListIndex].isEmpty())) {
                linkedListIndex++;
            }

            elementIndex++;

            if (elementIndex >= linkedLists[linkedListIndex].size()) {
                linkedListIndex++;

                while (linkedListIndex < linkedLists.length &&
                        (linkedLists[linkedListIndex] == null || linkedLists[linkedListIndex].isEmpty())) {
                    linkedListIndex++;
                }

                elementIndex = 0;
            }

            if (linkedListIndex < linkedLists.length && !linkedLists[linkedListIndex].isEmpty()) {
                value = linkedLists[linkedListIndex].get(elementIndex);
                elementsCount++;
            }

            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size()];
        Iterator<T> iterator = new HashTableIterator();

        for (int i = 0; i < objects.length; i++) {
            objects[i] = iterator.next();
        }

        return objects;
    }

    @Override
    public boolean add(T data) {
        int index = Math.abs(data == null ? 0 : data.hashCode() % linkedLists.length);

        if (linkedLists[index] == null) {
            linkedLists[index] = new LinkedList<>();
        }

        linkedLists[index].add(data);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o == null ? 0 : o.hashCode() % linkedLists.length);

        if (linkedLists[index] == null) {
            return false;
        }

        return linkedLists[index].remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        if (c == null) {
            return false;
        }

        for (Object object : c) {
            add((T) object);
        }

        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(linkedLists, null);
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        for (Object object : c) {
            if (!contains(object)) {
                while (contains(object)) {
                    remove(object);
                }
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null) {
            return false;
        }

        for (Object object : c) {
            while (contains(object)) {
                remove(object);
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (a.length < size()) {
            a = (Object[]) Array.newInstance(a.getClass().getComponentType(), size());

            return a;
        }

        if (a.length > size()) {
            for (int i = size(); i < a.length; i++)
                a[i] = null;
        }

        System.arraycopy(linkedLists, 0, a, 0, size());

        return a;
    }
}
