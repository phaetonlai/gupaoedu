package com.phantom.algorithm.solution.numberic;

/**
 * <p>两数相加</p>
 * 给出两个<b>非空</b>的链表用来表示两个非负的整数。其中，他们各自的位数是
 * 按照<b>逆序</b>的方式存储的，并且它们的每一个节点只能存储<b>一位</b>数字。<br/>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示他们的和。<br/>
 * 我们可以假设除了数字 0 之外，这两个数都不会以 0 开头。<br/>
 *
 * <p>示例：</p>
 *   输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *   输出：7 -> 0 -> 8
 *   原因：342 + 465 = 807
 *
 */
public class TwoInverseNumberAddSolution {

    public static void main(String[] args) {
//        ListNode n1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode n2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode n1 = new ListNode(0, new ListNode(8));
        ListNode n2 = new ListNode(5);
        ListNode r = add(n1, n2);
        System.out.println("(" + n1 + ") + (" + n2 + ") = (" + r + ")");
    }

    private static ListNode add(ListNode n1, ListNode n2) {
        ListNode p = n1, q = n2;

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = ((p != null) ? p.val : 0);
            int y = ((q != null) ? q.val : 0);
            int sum = x + y + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        // 额外的进位
        if (carry > 0) current.next = new ListNode(carry);

        return dummyHead.next;
    }

    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return this.next == null ? (this.val + "") : (this.val + " -> " + this.next);
        }
    }
}
