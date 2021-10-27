package com;

public class Main {

    public static void main(String[] args) {

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        Queue queue = new Queue();
        queue.push(3);
        queue.push(2);
        queue.push(1);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }


}


class Stack<T> {
    volatile Node head;

    public void push(T value) {
        Node inputNode = new Node(value);
        synchronized (this) {
            inputNode.next = head;
            head = inputNode;
        }
    }

    public Object pop() {
        if (head != null) {
            Node currentHead = head;
            synchronized (this) {
                head = head.next;
            }
            return currentHead.value;
        } else {
            throw new RuntimeException("head is null");
        }
    }

}

class Queue<T> {
    volatile Node head;
    volatile Node previous;

    public Queue() {
        head = previous = null;
    }

    public void push(T value) {
        Node inputNode = new Node(value);
        synchronized (this) {
            if (head == null) {
                head = inputNode;
            } else {
                previous.next = inputNode;
            }
            previous = inputNode;
        }
    }

    public Object pop() {
        Node currentHead = head;
        head = head.next;
        return currentHead.value;
    }


}


class Node<T> {
    T value;
    Node next;

    public Node(T value) {
        this.value = value;
    }
}

