package ru.academits.khanov.hashtable;

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
            if (linkedList != null) {
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
        return linkedLists[Math.abs(o.hashCode() % linkedLists.length)].contains(o);
    }

    //TODO +++++++++++++++++++++++++++++++
    private class HashTableIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int linkedListsIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        @Override
        public T next() {
            if (currentIndex + 1 > size()) {
                throw new NoSuchElementException("Отсутствует следующий элемент");
            }


            currentIndex++;

            int count = 0;
            T value = null;

            for (LinkedList<T> linkedList : linkedLists) {
                if (linkedList != null) {
                    count += linkedList.size();
                }

                if (currentIndex <= count && linkedList != null) {
                    return value = linkedList.get(currentIndex - count + linkedList.size());
                }
            }

            return value;
        }
    }

    @Override
    public Iterator iterator() {
        return new HashTableIterator();
    }

    //TODO +++++++++++++++++++++++++++++++
    @Override
    public Object[] toArray() {
        return new Object[0];
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

    //TODO +++++++++++++++++++++++++++++++
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
