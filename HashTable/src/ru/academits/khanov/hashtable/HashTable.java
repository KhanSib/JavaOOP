package ru.academits.khanov.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] lists;
    private int length;
    private int changesCount;

    public HashTable() {
        ArrayList<Object>[] lists = new ArrayList[10];
        this.lists = (ArrayList<T>[]) lists;
    }

    public HashTable(int size) {
        if (size <= 0) {
            throw new NullPointerException("Размер не может быть нулевым или отрицательным");
        }

        ArrayList<Object>[] lists = new ArrayList[size];
        this.lists = (ArrayList<T>[]) lists;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ArrayList<T> list : lists) {
            if (list != null) {
                stringBuilder.append(list);
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private int getIndex(Object o) {
        return o == null ? 0 : Math.abs(o.hashCode() % lists.length);
    }

    private class HashTableIterator implements Iterator<T> {
        private int elementsCount = 0;
        private int arrayListIndex = 0;
        private int elementIndex = -1;
        private final int initialChangesCount = changesCount;

        @Override
        public boolean hasNext() {
            return elementsCount + 1 <= length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Отсутсвует следующий элемент");
            }

            if (initialChangesCount != changesCount) {
                throw new ConcurrentModificationException("Коллекция изминилась во время обхода");
            }

            T value = null;

            while (arrayListIndex < lists.length &&
                    (lists[arrayListIndex] == null || lists[arrayListIndex].isEmpty())) {
                arrayListIndex++;
            }

            elementIndex++;

            if (elementIndex >= lists[arrayListIndex].size()) {
                arrayListIndex++;
                elementIndex = 0;

                while (arrayListIndex < lists.length &&
                        (lists[arrayListIndex] == null || lists[arrayListIndex].isEmpty())) {
                    arrayListIndex++;
                }
            }

            if (arrayListIndex < lists.length && !lists[arrayListIndex].isEmpty()) {
                value = lists[arrayListIndex].get(elementIndex);
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
        int i = 0;

        for (T element : this) {
            objects[i] = element;
            i++;
        }

        return objects;
    }

    @Override
    public boolean add(T data) {
        int index = getIndex(data);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(data);
        length++;
        changesCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
            length--;
            changesCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        int initialChangesCount = changesCount;

        for (T element : c) {
            add(element);
        }

        return initialChangesCount != changesCount;
    }

    @Override
    public void clear() {
        if (length > 0) {
            for (ArrayList<T> list : lists) {
                if (list != null && !list.isEmpty()) {
                    list.clear();
                }
            }

            changesCount++;
            length = 0;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        int initialChangesCount = changesCount;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int initialListLength = list.size();

                list.retainAll(c);
                int currentListLength = list.size();

                changesCount++;
                length -= initialListLength - currentListLength;
            }
        }

        return initialChangesCount != changesCount;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        int initialChangesCount = changesCount;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int initialListLength = list.size();

                list.removeAll(c);
                int currentListLength = list.size();

                changesCount++;
                length -= initialListLength - currentListLength;
            }
        }

        return initialChangesCount != changesCount;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (a.length < length) {
            return (T1[]) Arrays.copyOf(toArray(), length, a.getClass());
        }

        if (a.length > length) {
            a[length] = null;
        }

        System.arraycopy(toArray(), 0, a, 0, length);

        return a;
    }
}