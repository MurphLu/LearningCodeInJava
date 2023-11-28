package org.algorithm.leetcode.daily;

/**
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * 请你完成 FrontMiddleBack 类：
 *
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 *
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 */
public class FrontMiddleBackQueue {
    public static void main(String[] args) {
        FrontMiddleBackQueue fmbq = new FrontMiddleBackQueue();
        System.out.println(fmbq.popMiddle());
        System.out.println(fmbq.popMiddle());
        System.out.println(fmbq.popMiddle());
        System.out.println(fmbq.popMiddle());
        fmbq.pushMiddle(1);
        fmbq.pushMiddle(2);
        fmbq.pushMiddle(3);
        fmbq.pushFront(25);
        fmbq.pushMiddle(864705);
        fmbq.pushMiddle(689862);
        fmbq.pushMiddle(385730);
        System.out.println(fmbq.popMiddle());
        System.out.println(fmbq.popMiddle());
        fmbq.pushMiddle(921846);
        System.out.println(fmbq.popMiddle());
        fmbq.pushMiddle(595636);
        fmbq.pushMiddle(426540);
        fmbq.pushMiddle(181532);
        fmbq.pushMiddle(314738);
        fmbq.pushMiddle(663785);
        fmbq.pushFront(711612);
        System.out.println(fmbq.popMiddle());
        System.out.println(fmbq.popMiddle());
        System.out.println(fmbq.popMiddle());
        fmbq.pushMiddle(947300);
        fmbq.pushBack(362189);
        System.out.println(fmbq.popMiddle());
//        fmbq.popMiddle();","popMiddle","pushMiddle","popMiddle","pushMiddle","pushMiddle","pushMiddle","pushMiddle","pushMiddle","pushFront","popMiddle","popMiddle","popMiddle","pushMiddle","pushBack","popMiddle"
        String[] arr = new String[]{
                "popMiddle","popMiddle","popMiddle","popMiddle","pushMiddle","pushFront","pushMiddle","pushMiddle","pushMiddle","popMiddle","popMiddle","pushMiddle","popMiddle","pushMiddle","pushMiddle","pushMiddle","pushMiddle","pushMiddle","pushFront","popMiddle","popMiddle","popMiddle","pushMiddle","pushBack","popMiddle"
        };
        int[] arrNum = new int[]{
                0,0,0,0,820854,25,864705,689862,385730,0,0,921846,0,595636,426540,181532,314738,663785,711612,0,0,0,947300,362189,0
        };
        for (int i = 0; i< arr.length; i++) {
            if(arr[i].startsWith("pop")) {
                System.out.println("System.out.println(fmbq."+arr[i] + "());");
            } else {
                System.out.println("fmbq."+arr[i] + "(" + arrNum[i] + ");");
            }
        }
    }
    static class ListNode {
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode pre, ListNode next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    int size;
    ListNode head;
    ListNode mid;
    ListNode tail;

    public FrontMiddleBackQueue() {
        this.size = 0;
        this.head = null;
        this.mid = null;
        this.tail = null;
    }

    public void pushFront(int val) {
        if (this.head == null) {
            pushFirst(val);
            return;
        }
        ListNode node = new ListNode(val);
        node.next = this.head;
        this.head.pre = node;
        this.head = node;
        this.size++;
        if (size % 2 == 0) {
            mid = mid.pre;
        }
    }

    public void pushMiddle(int val) {
        if (this.head == null) {
            pushFirst(val);
            return;
        }
        ListNode node = new ListNode(val);
        if (this.size == 1) {
            this.mid.pre = node;
            this.mid = node;
            this.head = node;
        } else {
            if (size % 2 == 0) {
                this.mid = this.mid.next;
            }
            node.next = this.mid;
            node.pre = this.mid.pre;
            this.mid.pre.next = node;
            this.mid.pre = node;
            this.mid = node;
        }
        this.size++;
    }

    public void pushBack(int val) {
        if (this.head == null) {
            pushFirst(val);
            return;
        }
        ListNode node = new ListNode(val);
        this.tail.next = node;
        node.pre = this.tail;
        this.tail = node;
        this.size++;
        if (this.size % 2 == 1) {
            this.mid = this.mid.next;
        }
    }

    public int popFront() {
        if(size == 0) {
            return -1;
        }
        if (size == 1) {
            return popTheLastOne();
        }
        if (this.size % 2 == 0) {
            this.mid = this.mid.next;
        }
        int val = this.head.val;
        this.head = this.head.next;
        this.head.pre = null;
        this.size--;
        return val;
    }

    public int popMiddle() {
        if(size == 0) {
            return -1;
        }
        if (size == 1) {
            return popTheLastOne();
        }
        int val = this.mid.val;
        if (this.size == 2) {
            this.mid = this.mid.next;
            this.head = this.mid;
            this.mid.pre = null;
        } else {
            this.mid.pre.next = this.mid.next;
            this.mid.next.pre = this.mid.pre;
            this.mid = this.size % 2 == 1 ? this.mid.pre : this.mid.next;
        }
        this.size--;
        return val;
    }

    public int popBack() {
        if(size == 0) {
            return -1;
        }
        if (size == 1) {
            return popTheLastOne();
        }
        if (this.size % 2 == 1) {
            this.mid = this.mid.pre;
        }
        int val = this.tail.val;
        this.tail = this.tail.pre;
        this.tail.next = null;
        this.size--;
        return val;
    }

    private void pushFirst(int val) {
        ListNode node = new ListNode(val);
        this.head = node;
        this.mid = node;
        this.tail = node;
        this.size = 1;
    }

    private int popTheLastOne() {
        int val = head.val;
        this.head = null;
        this.mid = null;
        this.tail = null;
        this.size = 0;
        return val;
    }
}
