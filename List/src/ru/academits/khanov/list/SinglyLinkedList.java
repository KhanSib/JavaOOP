package ru.academits.khanov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;
        head.setNext(null);
        count++;
    }

    public SinglyLinkedList(T[] listItemsData) {
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


    public ListItem<T> getFirst() {
        if (count == 0) {
            return null;
        }

        return head;
    }

    public ListItem<T> getListItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        ListItem<T> currentListItem = head;

        for (int i = 0; i < index; i++) {
            currentListItem = currentListItem.getNext();
        }

        return currentListItem;
    }

    public T setListItem(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        ListItem<T> currentListItem = head;

        for (int i = 0; i < index; i++) {
            currentListItem = currentListItem.getNext();
        }

        T oldValueListItem = currentListItem.getData();
        currentListItem.setData(data);

        return oldValueListItem;
    }

    public T deleteListItem(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        ListItem<T> currentListItem = head;

        if (index == 0) {
            head = head.getNext();
            count--;

            return currentListItem.getData();
        }

        for (int i = 0; i < index - 1; i++) {
            currentListItem = currentListItem.getNext();
        }

        T deletedValue = currentListItem.getNext().getData();

        currentListItem.setNext(currentListItem.getNext().getNext());

        count--;
        return deletedValue;
    }

    public void insertListItemAsFirst(ListItem<T> listItem) {
        if (count == 0) {
            head = listItem;
            listItem.setNext(null);
        } else {
            listItem.setNext(head);
            head = listItem;
        }

        count++;
    }

    public void insertListItem(int index, ListItem<T> listItem) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        if (index == 0) {
            listItem.setNext(head);
            head = listItem;
        } else {
            ListItem<T> currentListItem = head;

            for (int i = 0; i < index - 1; i++) {
                currentListItem = currentListItem.getNext();
            }

            listItem.setNext(currentListItem.getNext());
            currentListItem.setNext(listItem);

        }

        count++;
    }

    public boolean deleteListItemByValue(T value) {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext().getData() == value) {
                p.setNext(p.getNext().getNext());

                return true;
            }
        }

        return false;
    }

    public T deleteFirstListItem() {
        T deletedValue = head.getData();

        head = head.getNext();

        return deletedValue;
    }

    public void reverse() {
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
        ListItem<T> listItem = new ListItem<>();
        SinglyLinkedList<T> singlyLinkedList = new SinglyLinkedList<>(listItem);

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