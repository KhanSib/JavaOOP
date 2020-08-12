package ru.academits.khanov.arraylist;

import java.lang.reflect.Array;
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
            a = (T1[]) Array.newInstance(a.getClass().getComponentType(), length);

            return a;
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
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index :" + index);
        }

        if (c == null) {
            return false;
        }

        if (length + c.size() > elements.length) {
            ensureCapacity(length + c.size());
        }

        Iterator<? extends T> iterator = c.iterator();
        int end = index + c.size();

        if (index < length) {
            System.arraycopy(elements, index, elements, end, length - index);
        }

        length += c.size();

        for (; index < end; index++) {
            elements[index] = iterator.next();
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
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
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }

        for (T element : elements) {
            if (!c.contains(element)) {
                while (contains(element)) {
                    remove(element);
                }
            }
        }

        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, length, null);
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index :" + index);
        }

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index :" + index);
        }

        T oldElement = elements[index];

        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index :" + index);
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
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера списка, index :" + index);
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
        for (int i = length - 1; i >= 0; i--) {
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