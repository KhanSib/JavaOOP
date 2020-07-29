package ru.academits.khanov.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int length;

    public ArrayList(T[] items) {
        elements = Arrays.copyOf(items, items.length);
        length = items.length;
    }

    public ArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
        }

        if (length > 0) {
            stringBuilder.append(elements[length - 1]);
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
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
        if (o == null) {
            return false;
        }

        for (Object item : elements) {
            if (item.equals(o)) {
                return true;
            }
        }

        return false;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            currentIndex++;
            return (T) elements[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        System.arraycopy(elements, 0, a, 0, length);

        return a;
    }

    @Override
    public boolean add(T t) {
        if (length >= elements.length) {
            increaseCapacity();
        }

        elements[length] = t;
        length++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            elements[i] = null;
        }

        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }

        elements[index] = element;

        return element;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        if (index >= length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }

        T removedElement = elements[index];

        System.arraycopy(elements, index + 1, elements, index, length - 1);

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}