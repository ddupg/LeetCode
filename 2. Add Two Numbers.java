/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode ans = solve(l1, l2);
		for (ListNode cur = ans; cur != null; cur = cur.next) {
			if (cur.val >= 10) {
				if (cur.next == null) {
					cur.next = new ListNode(cur.val / 10);
				} else {
					cur.next.val += cur.val / 10;
				}
				cur.val %= 10;
			}
		}
		return ans;
	}

	private ListNode solve(ListNode l1, ListNode l2) {
		ListNode ans = null;
		if (l1 != null || l2 != null) {
			int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
			ans = new ListNode(val);
			ans.next = solve((l1 == null ? null : l1.next), (l2 == null ? null : l2.next));
		}
		return ans;
	}
}
