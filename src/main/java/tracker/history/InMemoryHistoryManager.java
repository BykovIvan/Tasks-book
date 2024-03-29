package main.java.tracker.history;

import main.java.tracker.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager<T extends Task> implements HistoryManager<T> {

    /**
     * Класс Ноды для собсвенного двусвязного списка LinkedList
     *
     * @param <T>
     */
    private static class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;

        }
    }

    private Node<T> first;                       //Переменная указателя на первый элемент
    private Node<T> last;                       //Переменная указателя на последний элемент
    private int size;                          //Перемення для сохранения размера списка

    private final Map<Integer, Node<T>> mapList = new HashMap<>();  //Для хранения нод у собственного списка и получения по id
    private final List<T> historyArrayList = new ArrayList<>();     //Лист для формирования списка на выход

    @Override
    public void add(T task) {
        int id = task.getIdTask();
        if (mapList.containsKey(id)) {
            removeNode(mapList.get(id));         //Удаляем старый
            linkLast(task, id);                  //добавляем ноду с свой список
        } else {
            linkLast(task, id);
        }
    }

    @Override
    public List<T> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int idTask) {
        if (mapList.containsKey(idTask)){
            removeNode(mapList.get(idTask));
            mapList.remove(idTask);
        }

    }

    @Override
    public boolean contains(int ID) {
        for (Integer integer : mapList.keySet()) {
            if (ID == integer) {
                return true;
            }
        }
        return false;
    }

    //Методы от списка

    /**
     * Метод добавления задачи в конец списка
     *
     * @param value
     * @param id
     */
    private void linkLast(T value, int id) {
        if (size == 0) {
            final Node<T> node = new Node<>(null, value, null);
            mapList.put(id, node);                                      //кладем в таблицу для сохранения id и ноды
            first = node;
            last = node;
            size++;
            return;
        }
        final Node<T> oldLast = last;                                   //Создаем ноду для сохранения последней текущей
        final Node<T> newNode = new Node<>(oldLast, value, null);  //Создаем ноду последнюю (В конце)
        mapList.put(id, newNode);
        last = newNode;                                                 //Последней присваевываем последнюю ноду
        oldLast.next = newNode;                                         //у старой ноды указываем новую ссылку на новый
        size++;
    }

    /**
     * Метод собираения всех задач в обычный ArrayList
     *
     * @return
     */
    private List<T> getTasks() {
        historyArrayList.clear();
        Node<T> cur = this.first;
        while (cur != null) {
            historyArrayList.add(cur.value);
            cur = cur.next;
        }
        return historyArrayList;
    }

    /**
     * Метод удаления ноды из списка
     *
     * @param node
     */
    private void removeNode(Node<T> node) {
        final Node<T> next = node.next;
        final Node<T> prev = node.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node = null;
        size--;
    }

    /**
     * Метод вывода размера списка
     *
     * @return
     */
    private int size() {
        return this.size;
    }


}


