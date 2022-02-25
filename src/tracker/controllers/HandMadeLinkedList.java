package tracker.controllers;

import tracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandMadeLinkedList<T extends Task> {
    private Node<Task> head;                       //Переменная указателя на первый элемент
    private Node<Task> tail;                       //Переменная указателя на последний элемент
    private int size;                          //Перемення для сохранения размера списка
    private Map<Integer, Node<Task>> mapList;  //Для хранения нод у собственного списка и получения по id
    private List<Task> list;

    public HandMadeLinkedList() {
        mapList = new HashMap<>();
        list = new ArrayList<>();
    }

    public void linkLast(Task element, int id) {
        if (head == null) {
            head = tail = new Node(null, element, null);
            mapList.put(id, tail);

            size++;
        } else {
            final Node<Task> oldTail = tail;
            final Node<Task> newNode = new Node<>(oldTail, element, null);
            tail = newNode;
            mapList.put(id, tail);
            if (oldTail == null) {
                head = newNode;
            } else {
                oldTail.next = newNode;
            }
            size++;
        }
    }

    public List<Task> getTasks() {
        //собрать все задачи в ArrayList

//        Node<Task> node = mapList.get();

        return list;
    }

    public void removeNode(Node<Task> node) {
        //Удалить самму ноду как это делается в списках
        //Удалить ноду и ид в мапе
    }

    //Вывести размер списка
    public int size() {
        return this.size;
    }

}
