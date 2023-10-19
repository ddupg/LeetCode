/*
 * @lc app=leetcode.cn id=1726 lang=java
 *
 * [1726] 同积元组
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>(n * n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cnt.compute(nums[i] * nums[j], (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int rst = 0;
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            rst += e.getValue() * (e.getValue() - 1) / 2;
        }
        return rst * 8;
    }
}
// @lc code=end

