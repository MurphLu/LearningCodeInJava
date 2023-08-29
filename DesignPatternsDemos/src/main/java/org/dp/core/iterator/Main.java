package org.dp.core.iterator;

public class Main {
}

interface Collection_ {
    void add(Object o);
    int size();
    Iterator_ iterator();
}

interface Iterator_ {
    boolean hasNext();
    Object next();
}

class ArrayList_ implements Collection_{
    Object[] objects = new Object[10];
    private int index = 0;
    public void add(Object o) {
        if(index == objects.length) {
            Object[] newObjects = new Object[objects.length * 2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            for (int i = 0; i < objects.length; i++) {
                newObjects[i] = objects[i];
            }
            objects = newObjects;
        }
        objects[index] = o;
        index += 1;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator_ iterator() {
        return new ArrayListIterator();
    }

    class ArrayListIterator implements Iterator_ {
        int index_ = 0;

        @Override
        public boolean hasNext() {
            return index_ < index;
        }

        @Override
        public Object next() {
            index_ += 1;
            return objects[index_];
        }
    }
}

class LinkedList_ implements Collection_{
    Node head = null;
    Node tail = null;
    private int size;

    public void add(Object o) {
        Node n = new Node(o);
        n.next = null;

        if (head == null) {
            head = n;
        }else {
            tail.next = n;
        }
        tail = n;
        size++;
    }

    class Node {
        Object o;
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator_ iterator() {
        return new LikedListIterator_();
    }

    class LikedListIterator_ implements Iterator_ {
        Node curr = head;

        @Override
        public boolean hasNext() {
            return curr.next != null;
        }

        @Override
        public Object next() {
            curr = curr.next;
            return curr;
        }
    }
}


