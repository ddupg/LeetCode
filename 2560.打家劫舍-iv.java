/*
 * @lc app=leetcode.cn id=2560 lang=java
 *
 * [2560] 打家劫舍 IV
 */

// @lc code=start

import java.util.PriorityQueue;

class Solution {
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            ranks[i] = nums[i];
        }
        Arrays.sort(ranks);
        int l = 0, r = n - 1;
        while (l < r) {
            int m = l + r >> 1;
            if (!dp(nums, ranks[m], k)) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        System.out.println(l);
        return ranks[l];
    }

    private boolean dp(int[] nums, int max, int k) {
        int [][] d = new int[2][2];
        if (nums[0] <= max) {
            d[0][1] = 1;
        }
        int curr = 1;
        for (int i = 1; i < nums.length; i ++, curr ^= 1) {
            int pre = curr ^ 1;
            d[curr][0] = Math.max(d[pre][0], d[pre][1]);
            d[curr][1] = nums[i] <= max ? d[pre][0] + 1 : 0;
            if (d[curr][0] >= k || d[curr][1] >= k) {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end

