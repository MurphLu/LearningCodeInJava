package org.algorithm.slidingWindow;

public class Deque<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    private int size;

    public Deque() {
        size = 0;
    }

    static class Node<T> {
        Node<T> pre;
        Node<T> next;
        T val;

        public Node(T val) {
            this.val = val;
        }
    }

    public Deque(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public void pushHead(T val) {
        Node<T> node = new Node<>(val);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            head.pre = node;
            node.next = head;
            head = node;
        }
        size ++;
    }

    public T popHead() {
        if (size >= 1) {
            T val = head.val;
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.pre = null;
            }
            size --;
            return val;
        } else {
            return null;
        }
    }

    public void pushTail(T val) {
        Node<T> node = new Node<>(val);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size ++;
    }

    public T popTail() {
        if (size >= 1) {
            T val = tail.val;
            tail = tail.pre;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            size --;
            return val;
        } else {
            return null;
        }
    }

    public int getSize() {
        return size;
    }

    public T getTail() {
        return tail == null ? null : tail.val;
    }

    public T getHead() {
        return head == null ? null : head.val;
    }

    @Override
    public String toString() {
        Node temp = head;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (temp != null) {
            builder.append(temp.val.toString());
            if (temp != tail) {
                builder.append(", ");
            }
            temp = temp.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isNotEmpty() {
        return size > 0;
    }
}
