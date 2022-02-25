package tracker.controllers;

import tracker.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> historyList;

    public InMemoryHistoryManager() {
        historyList = new ArrayList<>();
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public List<Task> getHistory() {
        return null;
    }

    @Override
    public void remove(int Id) {


    }

    //Класс для собсвенного списка

    public static class LinkidHistoryList<T>{
        private Node<T> head;                       //Переменная указателя на первый элемент
        private Node<T> tail;                       //Переменная указателя на последний элемент
        private int size;                          //Перемення для сохранения размера списка

        public void addFirst(T element) {
            final Node<T> oldHead = head;
            final Node<T> newNode = new Node<T>(null, element, oldHead);
            head = newNode;
            if (oldHead == null)
                tail = newNode;
            else
                oldHead.previous = newNode;
            size++;
        }

        public T getFirst() {
            final Node<T> curHead = head;
            if (curHead == null)
                throw new NoSuchElementException();
            return head.data;
        }

        public void addLast(T element) {
            final Node<T> oldTail = tail;
            final Node<T> newNode = new Node<>(oldTail, element, null);
            tail = newNode;
            if (oldTail == null){
                head = newNode;
            }else{
                oldTail.next = newNode;
            }
            size++;
            // Реализуйте метод// Реализуйте метод
        }

        public T getLast() {
            final Node<T> curTail = tail;
            if (curTail == null){
                throw new NoSuchElementException();
            }
            return tail.data;
            // Реализуйте метод// Реализуйте метод
        }

        public int size() {
            return this.size;
        }

    }


}
//    @Override
//    public void add(Task task) {
//        if (historyList.size() < 100) {
//            historyList.add(task); // добавление задачи в лист истории
//        } else {
//            historyList.remove(0);
//            historyList.add(99, task); // добавление задачи в лист истории
//        }
//    }
//
//    @Override
//    public List<Task> getHistory() {
//        int sizeArray = historyList.size();
//        if (sizeArray < 10) return historyList;
//
//        List<Task> last10TaskList = new ArrayList<>();
//        for (int i = historyList.size() - 10; i < historyList.size(); i++) {
//            last10TaskList.add(historyList.get(i));
//        }
//        return last10TaskList;
//    }
//
//    @Override
//    public void remove(Task task) {
//        if (historyList.contains(task)){
//            historyList.remove(task);
//        }
//    }

