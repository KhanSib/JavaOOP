package ru.academits.khanov.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T[] listItemsData) {
        if (listItemsData == null) {
            throw new NullPointerException("Массив не может быть null");
        }

        ListItem<T> currentListItem = new ListItem<>(listItemsData[listItemsData.length - 1], null);

        for (int i = listItemsData.length - 2; i >= 0; i--) {
            currentListItem = new ListItem<>(listItemsData[i], currentListItem);
        }

        head = currentListItem;
        count = listItemsData.length;
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private ListItem<T> getListItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка, index: " + index);
        }

        ListItem<T> currentListItem = head;

        for (int i = 0; i < index; i++) {
            currentListItem = currentListItem.getNext();
        }

        return currentListItem;
    }

    public T getItem(int index) {
        return getListItem(index).getData();
    }

    public T setItem(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка, index: " + index);
        }

        T removedData = getListItem(index).getData();
        getListItem(index).setData(data);

        return removedData;
    }

    public T deleteItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка, index: " + index);
        }

        if (index == 0) {
            return deleteFirstItem();
        }

        ListItem<T> previousListItem = getListItem(index - 1);

        T deletedValue = previousListItem.getNext().getData();

        previousListItem.setNext(previousListItem.getNext().getNext());

        count--;
        return deletedValue;
    }

    public void insertItemAsFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void insertItem(int index, T data) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка, index: " + index);
        }

        if (index == 0) {
            insertItemAsFirst(data);
        } else {
            ListItem<T> listItem = new ListItem<>(data);
            ListItem<T> previousListItem = getListItem(index - 1);

            listItem.setNext(previousListItem.getNext());
            previousListItem.setNext(listItem);

            count++;
        }
    }

    public boolean deleteItemByValue(T value) {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext().getData() != null && p.getNext().getData().equals(value)) {
                p.setNext(p.getNext().getNext());

                count--;
                return true;
            }
        }

        return false;
    }

    public T deleteFirstItem() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        T deletedValue = head.getData();

        head = head.getNext();

        count--;
        return deletedValue;
    }

    public void reverse() {
        if (head == null || count == 1) {
            return;
        }

        ListItem<T> previous = head;
        ListItem<T> current = head.getNext();
        ListItem<T> next = head.getNext().getNext();

        head.setNext(null);

        while (next != null) {
            current.setNext(previous);

            previous = current;
            current = next;
            next = next.getNext();
        }

        head = current;
        head.setNext(previous);
    }

    public SinglyLinkedList<T> getCopy() {
        if (head == null) {
            return new SinglyLinkedList<T>();
        }

        ListItem<T> listItem = new ListItem<>(head.getData(), head.getNext());
        SinglyLinkedList<T> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.head = listItem;
        singlyLinkedList.count = count;

        for (ListItem<T> p = head; p.getNext() != null; p = p.getNext()) {
            listItem.setData(p.getData());
            listItem.setNext(new ListItem<>(p.getNext().getData()));
            listItem = listItem.getNext();
        }

        return singlyLinkedList;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            stringBuilder.append(p.getData());

            if (p.getNext() != null) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}