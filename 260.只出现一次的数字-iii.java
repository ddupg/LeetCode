/*
 * @lc app=leetcode.cn id=260 lang=java
 *
 * [260] 只出现一次的数字 III
 */

// @lc code=start

import java.util.function.Function;

class Solution {
    public int[] singleNumber(int[] nums) {
        int x = xor(nums, a -> true);
        int bit = x & -x;
        return new int[]{xor(nums, a -> (a & bit) == 0), xor(nums, a -> (a & bit) != 0)};
    }

    private int xor(int[] nums, Function<Integer, Boolean> filter) {
        int rst = 0;
        for (int x : nums) {
            if (filter.apply(x)) {
                rst ^= x;
            }
        }
        return rst;
    }
}
// @lc code=end

