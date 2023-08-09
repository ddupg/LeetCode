/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
 */

// @lc code=start

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // mock一个头节点，用于模拟前一组链表，避免边界条件处理
        ListNode mockHead = new ListNode(0, head);
        ListNode preGroupTail = mockHead;
        ListNode currGroupHead = head;
        while (true) {
            ListNode currGroupTail = goN_1Steps(currGroupHead, k);
            if (currGroupTail == null) {
                // 不足k个一组
                preGroupTail.next = currGroupHead;
                break;
            }

            ListNode nextGroupHead = currGroupTail.next;
            // 反转后，首尾互换
            ListNode reverseHead = reverse(currGroupHead, k);
            currGroupTail = currGroupHead;
            // 和上一组连接起来
            preGroupTail.next = reverseHead;
            // 初始化下一组的变量
            currGroupHead = nextGroupHead;
            preGroupTail = currGroupTail;
        }
        return mockHead.next;
    }

    /**
     * 反转连续k个节点，也就是反转一组
     * @return 返回反转后的头节点
     */
    private ListNode reverse(ListNode head, int n) {
        ListNode pre = null, curr = head;
        for (int i = 0; i < n && curr != null; i++) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 往前走k-1步，走到当前组的最后一个节点
     * @return 如果走到null，说明这组不足k个
     */
    private ListNode goN_1Steps(ListNode head, int n) {
        for (int i = 0; i < n - 1 && head != null; i++) {
            head = head.next;
        }
        return head;
    }
}
// @lc code=end

