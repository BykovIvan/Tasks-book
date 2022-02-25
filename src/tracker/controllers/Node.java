package tracker.controllers;

public class Node<Task> {
    public Task data;
    public Node<Task> next;
    public Node<Task> previous;

    public Node(Node<Task> previous, Task data, Node<Task> next) {
        this.previous = previous;
        this.data = data;
        this.next = next;

    }
}
