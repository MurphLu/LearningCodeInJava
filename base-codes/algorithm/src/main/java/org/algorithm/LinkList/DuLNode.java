package org.algorithm.LinkList;

public class DuLNode<T> {
    private final T val;
    private DuLNode<T> pre;
    private DuLNode<T> next;

    public DuLNode(T val, DuLNode<T> pre, DuLNode<T> next) {
        this.val = val;
        this.pre = pre;
        this.next = next;
    }

    public T getVal() {
        return val;
    }

    public DuLNode<T> getPre() {
        return pre;
    }

    public void setPre(DuLNode<T> pre) {
        this.pre = pre;
    }

    public DuLNode<T> getNext() {
        return next;
    }

    public void setNext(DuLNode<T> next) {
        this.next = next;
    }
}
