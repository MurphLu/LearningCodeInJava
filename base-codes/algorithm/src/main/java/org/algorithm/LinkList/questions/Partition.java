package org.algorithm.LinkList.questions;

import org.algorithm.LinkList.LNode;
import org.algorithm.LinkList.Utils;

//
public class Partition {
    public static void main(String[] args) {
        LNode<Integer> lNode = Utils.generate(10, 0, 10);
        Utils.printNode(lNode);
        LNode<Integer> lNode1 = partitionWithoutChangeVal(lNode, 5);
        Utils.printNode(lNode1);
    }

    // 不应该有这种操作😅
    public static void partition(LNode<Integer> lNode, int val) {
        LNode<Integer> start = lNode;
        while (start.getVal() < val) {
            start = start.getNext();
        }
        LNode<Integer> l = start;
        LNode<Integer> r = start;
        LNode<Integer> cur = start;
        while (cur != null) {
            if (cur.getVal() < val) {
                l.changeValue(cur);
                if(l == r) {
                    r = r.getNext();
                }
                l = l.getNext();
            } else {
                if (cur.getVal() == val){
                    r.changeValue(cur);
                    r = r.getNext();
                }
                cur = cur.getNext();
            }
        }
    }

    public static LNode<Integer> partitionWithoutChangeVal(LNode<Integer> lNode, int val){
        LNode<Integer> sh = null;
        LNode<Integer> st = null;
        LNode<Integer> eh = null;
        LNode<Integer> et = null;
        LNode<Integer> bh = null;
        LNode<Integer> bt = null;
        LNode<Integer> cur = lNode;

        while (cur != null) {
            if(cur.getVal() < val) {
                if(st == null) {
                    sh = cur;
                } else {
                    st.setNext(cur);
                }
                st = cur;
                cur = cur.getNext();
                st.setNext(null);
            } else if (cur.getVal() == val) {
                if(et == null) {
                    eh = cur;
                } else {
                    et.setNext(cur);
                }
                et = cur;
                cur = cur.getNext();
                et.setNext(null);
            } else {
                if(bt == null) {
                    bh = cur;
                } else {
                    bt.setNext(cur);
                }
                bt = cur;
                cur = cur.getNext();
                bt.setNext(null);
            }
        }
        LNode<Integer> head = sh != null ? sh : eh != null ? eh : bh;
        if (st != null) {
            st.setNext(eh != null ? eh : bh);
        }
        if (et != null) {
            et.setNext(bh);
        }
        return head;
    }
}
