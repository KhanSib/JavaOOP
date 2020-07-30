package ru.academits.khanov.list.main;

import ru.academits.khanov.list.SinglyLinkedList;
import ru.academits.khanov.list.ListItem;

public class Main {
    public static void main(String[] args) {
        ListItem<Integer> listItem1 = new ListItem<>(1);

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertItemAsFirst(5);
        list1.insertItemAsFirst(4);
        list1.insertItemAsFirst(3);
        list1.insertItemAsFirst(2);
        list1.insertItemAsFirst(1);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4});

        System.out.println("Узел 1: " + listItem1);

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);

        System.out.println("Первый элемент списка list2: " + list2.getFirst());
        System.out.println("Элемент по индексу 2 списка list2: " + list2.getItem(2));

        System.out.println("Меняем значение узла по индексу 2 на значение 100 списка list2: " + list2.setItem(2, 100));
        System.out.println("list2: " + list2);

        System.out.println("Удалили узел по индексу 0 списка list2: " + list2.deleteItem(0));
        System.out.println("list2: " + list2);

        list2.insertItemAsFirst(1);
        list2.insertItemAsFirst(2);
        System.out.println("Добавили узлы 1 и 2 как первые к списку list2: " + list2);

        list2.insertItem(3, 6);
        System.out.println("Результат вставки узел 6 в list2 по индексу 3: " + list2);

        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
        System.out.println("Размер списка 3: " + list3.getSize());

        System.out.println("Удалили узел по значению 100 списка list2: " + list2.deleteItemByValue(100));
        System.out.println("list2: " + list2);

        System.out.println("Удалили первый узел списка list2: " + list2.deleteFirstItem());
        System.out.println("list2: " + list2);

        list2.reverse();
        System.out.println("Результат разворота списка 2: " + list2);

        SinglyLinkedList<Integer> list4 = list2.getCopy();
        System.out.println("Копия list2, list4: " + list4);

        list2.reverse();
        System.out.println("Разворот list2: " + list2);
        System.out.println("list4: " + list4);

        SinglyLinkedList<Integer> list5 = new SinglyLinkedList<>(new Integer[]{0});
        list5.reverse();
        System.out.println("Результат разворота list5: " + list5);

        SinglyLinkedList<Integer> list6 = new SinglyLinkedList<>();
        list6.insertItemAsFirst(1);
        System.out.println("list6: " + list6);
    }
}