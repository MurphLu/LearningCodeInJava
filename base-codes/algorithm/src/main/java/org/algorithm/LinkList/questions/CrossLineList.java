package org.algorithm.LinkList.questions;

// 给定两个可能有环也可能无环的单链表，头节点头1和头2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回空值
//[要求]如果两个链表长度之和为N，时间复杂度请达到0(N)，额外空间复杂度
//请达到0(1)。

// 是否有环？
// 快慢节点
public class CrossLineList {
    public static void main(String[] args) {

    }

    public static Node circleEnter(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
            if (slow == fast){
                break;
            }
        }
        if (fast == null) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static Node crossNode(Node h1, Node h2) {
        // 获取入环节点
        Node loop1 = circleEnter(h1);
        Node loop2 = circleEnter(h2);
        // 如果都无环或者入环节点相同（以入环节点为end也可作为两个无环链表来处理），处理方式相同
        if(loop1 == loop2) {
            Node end = loop1 == null ? null : loop1.next;
            int l1 = 1, l2 = 1;
            Node e1 = h1;
            Node e2 = h2;
            while (e1.next != end) {
                l1 ++;
                e1 = e1.next;
            }
            while (e2.next != end) {
                l2 ++;
                e2 = e2.next;
            }
            if (e1 != e2) { return null; }
            Node longer = l1 > l2 ? h1 : h2;
            Node shorter = l1 > l2 ? h2 : h1;
            int f = 0;
            while (f < Math.abs(l1 - l2)) {
                longer = longer.next;
                f ++;
            }
            while (longer != shorter) {
                longer = longer.next;
                shorter = shorter.next;
            }
            return longer;
        }
        // 如果只有一个为 null，则一个有环一个无环，这是比不相交
        if (loop1 == null || loop2 == null) {
            return null;
        }

        // 如果节点不同，让一个入环节点在环上走一圈，
        // 如果环上有另外一个入环节点，那么返回任一入环节点都可
        // 否则 不相交
        Node loop2N = loop2.next;
        while (loop2 != loop2N){
            if (loop1 == loop2N) {
                return loop1;
                //return loop2;
            }
            loop2N = loop2N.next;
        }
        return null;
    }

    // 有环
    public static Node crossNodeWithCircle(Node h1, Node h2, Node loop1, Node loop2) {
        if(loop1 == null ^ loop2 == null) {
            return null;
        }

        Node loop2N = loop2.next;
        while (loop2 != loop2N){
            if (loop1 == loop2N) {
                return loop1;
                //return loop2;
            }
            loop2N = loop2N.next;
        }
        return null;
        // 一个有环，一个无环，不相交
        // 两个都有环，但不相交
        // 入环节点相同，无环链表相交问题，入环节点为终止节点
        // 入环节点不同，任一入环节点都可以
    }
}
