package tracker.controllers;

public class Node<E> {
    public E data;
    public Node<E> next;
    public Node<E> previous;

    public Node(Node<E> previous, E data, Node<E> next) {
        this.previous = previous;
        this.data = data;
        this.next = next;

    }
}
