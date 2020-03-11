package com.phantom.algorithm.solution.numberic;

/**
 * <p>两数相加</p>
 * 给出两个<b>非空</b>的链表用来表示两个非负的整数。其中，他们各自的位数是
 * 按照<b>正序</b>的方式存储的，并且它们的每一个节点只能存储<b>一位</b>数字。<br/>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示他们的和。<br/>
 * 我们可以假设除了数字 0 之外，这两个数都不会以 0 开头。<br/>
 *
 * <p>示例：</p>
 *   输入：(4 -> 4 -> 3) + (5 -> 6 -> 4)
 *   输出：1 -> 0 -> 0 -> 7
 *   原因：443 + 564 = 1007
 *
 */
public class TwoPositiveNumberAddSolution {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3, new ListNode(4, new ListNode(4)));
        ListNode n2 = new ListNode(4, new ListNode(6, new ListNode(5)));
//        ListNode n1 = new ListNode(0, new ListNode(8));
//        ListNode n2 = new ListNode(5);
        ListNode r = add(n1, n2);
        System.out.println("(" + n1 + ") + (" + n2 + ") = (" + r + ")");
    }

    private static ListNode add(ListNode n1, ListNode n2) {
        ListNode p = n1, q = n2;

        ListNode dummyTail = new ListNode(0);
        ListNode current = dummyTail;
        int carry = 0;
        while (p != null || q != null) {
            int x = ((p != null) ? p.val : 0);
            int y = ((q != null) ? q.val : 0);
            int sum = x + y + carry;
            carry = sum / 10;
            current.prev = new ListNode(sum % 10);
            current = current.prev;
            if (p != null) p = p.prev;
            if (q != null) q = q.prev;
        }
        // 额外的进位
        if (carry > 0) current.prev = new ListNode(carry);

        return dummyTail.prev;
    }

    static class ListNode {
        private int val;
        private ListNode prev;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.prev = next;
        }

        @Override
        public String toString() {
            return this.prev == null ? (this.val + "") : (this.prev + " -> " + this.val);
        }
    }
}
