package ru.academits.khanov.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int length;

    public ArrayList() {
    }

    public ArrayList(T[] items) {
        if (items != null) {
            elements = Arrays.copyOf(items, items.length);
            length = items.length;
        }
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IndexOutOfBoundsException("Вместимость массива не может быть нулевой или отрицательной");
        }

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

        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            if (currentIndex + 1 > length) {
                throw new NoSuchElementException("Отсутствует следующий элемент");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        if (a.length < length) {
            a = Arrays.copyOf(a, length);
        }

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
        if (indexOf(o) == -1) {
            return false;
        }

        remove(indexOf(o));
        return true;
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
    public boolean addAll(Collection<? extends T> c) {
        for (Object object : c) {
            add((T) object);
        }

        return true;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index >= length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }

        while (elements.length - length < c.size()) {
            increaseCapacity();
        }

        int collectionSize = c.size();
        Object[] collectionArray = c.toArray();

        length += collectionSize;

        System.arraycopy(elements, index,
                elements, index + collectionSize, length - index - collectionSize);

        System.arraycopy(collectionArray, 0, elements, index, index + collectionSize - 2);

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object object : c) {
            remove(object);
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < length; i++) {
            boolean needRemove = true;

            for (Object object : c) {
                if (elements[i].equals(object)) {
                    needRemove = false;
                    break;
                }
            }

            if (needRemove) {
                remove(i);
                i--;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for (T element : elements) {
            element = null;
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
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }

        elements[index] = element;

        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }

        if (length == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, length - index);
        elements[index] = element;

        length++;
    }

    @Override
    public T remove(int index) {
        if (index >= length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка");
        }

        T removedElement = elements[index];

        System.arraycopy(elements, index + 1, elements, index, length - index - 1);

        elements[length - 1] = null;
        length--;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i > 0; i--) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
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

    public void ensureCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IndexOutOfBoundsException("Вместимость не может быть нулевой или отрицательной");
        }

        if (capacity <= elements.length) {
            throw new IndexOutOfBoundsException("Вместимость должна быть больше начальной");
        }

        elements = Arrays.copyOf(elements, capacity);
    }

    public void trimToSize() {
        elements = Arrays.copyOf(elements, length);
    }
}