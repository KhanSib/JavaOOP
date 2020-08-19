package ru.academits.khanov.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int length;

    public ArrayList() {
        elements = (T[]) new Object[10];
    }

    public ArrayList(T[] items) {
        if (items != null) {
            elements = Arrays.copyOf(items, items.length * 2);
            length = items.length;
        }
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("Вместимость массива не может быть нулевой или отрицательной: "
                    + capacity);
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
        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int initialLength = length;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            if (initialLength != length) {
                throw new ConcurrentModificationException("Коллекция изминилась во время обхода");
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
            return (T1[]) Arrays.copyOf(elements, length, a.getClass());
        }

        if (a.length > length) {
            a[length] = null;
        }

        System.arraycopy(elements, 0, a, 0, length);

        return a;
    }

    @Override
    public boolean add(T t) {
        if (length == elements.length) {
            increaseCapacity();
        }

        elements[length] = t;
        length++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
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
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        if (index >= length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index: " + index);
        }

        ensureCapacity(length + c.size());

        int end = index + c.size();

        if (index < length) {
            System.arraycopy(elements, index, elements, end, length - index);
        }

        int initialLength = length;
        length += c.size();

        int i = index;

        for (T element : c) {
            elements[i] = element;
            i++;
        }

        return initialLength != length;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        int initialLength = length;

        for (Object object : c) {
            while (true) {
                int index = indexOf(object);

                if (index >= 0) {
                    remove(index);
                } else {
                    break;
                }
            }
        }


        return initialLength != length;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        int initialLength = length;

        for (T element : elements) {
            if (!c.contains(element)) {
                remove(element);
            }
        }

        return initialLength != length;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, length, null);
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index: " + index);
        }

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index: " + index);
        }

        T oldElement = elements[index];

        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index: " + index);
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
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index: " + index);
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
            if (elements[i] != null && elements[i].equals(o) || elements[i] == null && o == null) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (elements[i] != null && elements[i].equals(o) || elements[i] == null && o == null) {
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
        if (capacity > elements.length) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public void trimToSize() {
        if (elements.length != length) {
            elements = Arrays.copyOf(elements, length);
        }
    }
}