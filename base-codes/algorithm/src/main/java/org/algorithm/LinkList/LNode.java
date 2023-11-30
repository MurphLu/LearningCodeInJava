package org.algorithm.LinkList;

public class LNode<T> {
    private T val;
    private LNode<T> next;

    public LNode(T val, LNode<T> next) {
        this.val = val;
        this.next = next;
    }

    public T getVal() {
        return val;
    }

    public LNode<T> getNext() {
        return next;
    }

    public void setNext(LNode<T> next) {
        this.next = next;
    }

    public void changeValue(LNode<T> lNode) {
        T temp = this.val;
        this.val = lNode.val;
        lNode.val = temp;
    }
}
