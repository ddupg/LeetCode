/*
 * @lc app=leetcode.cn id=2530 lang=java
 *
 * [2530] 执行 K 次操作后的最大分数
 */

// @lc code=start

import java.util.PriorityQueue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.length, (a, b) -> Integer.compare(b, a));
        for (int x : nums) {
            pq.add(x);
        }
        long rst = 0;
        while (!pq.isEmpty() && k -- > 0) {
            int x = pq.poll();
            rst += x;
            pq.add((x + 2) / 3);
        }
        return rst;
    }
}
// @lc code=end

