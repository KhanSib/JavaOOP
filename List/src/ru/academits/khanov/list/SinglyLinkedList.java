package ru.academits.khanov.list;

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
            throw new NullPointerException("Список пуст");
        }

        if (count == 0) {
            return null;
        }

        return head.getData();
    }

    private ListItem<T> getListItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        ListItem<T> currentListItem = head;

        for (int i = 0; i < index; i++) {
            currentListItem = currentListItem.getNext();
        }

        return currentListItem;
    }

    public T getItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        return getListItem(index).getData();
    }

    public T setItem(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        ListItem<T> currentListItem = head;

        for (int i = 0; i < index; i++) {
            currentListItem = currentListItem.getNext();
        }

        T removedData = currentListItem.getData();
        currentListItem.setData(data);

        return removedData;
    }

    public T deleteItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        T deletedValue = head.getData();

        if (index == 0) {
            head = head.getNext();
            count--;

            return deletedValue;
        }

        deletedValue = getListItem(index - 1).getNext().getData();

        getListItem(index - 1).setNext(getListItem(index - 1).getNext().getNext());

        count--;
        return deletedValue;
    }

    public void insertItemAsFirst(T data) {
        ListItem<T> listItem = new ListItem<>(data);
        listItem.setNext(head);
        head = listItem;

        count++;
    }

    public void insertItem(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        if (index == 0) {
            insertItemAsFirst(data);
        } else {

            ListItem<T> listItem = new ListItem<>(data);

            listItem.setNext(getListItem(index - 1).getNext());
            getListItem(index - 1).setNext(listItem);
        }

        count++;
    }

    public boolean deleteItemByValue(T value) {
        if (value == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }

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
            throw new NullPointerException("Список не может быть null");
        }

        T deletedValue = head.getData();

        head = head.getNext();

        count--;
        return deletedValue;
    }

    public void reverse() {
        if (head == null) {
            throw new NullPointerException("Список не может быть null");
        }

        if (count == 1) {
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