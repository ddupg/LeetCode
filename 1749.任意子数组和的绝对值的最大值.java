/*
 * @lc app=leetcode.cn id=1749 lang=java
 *
 * [1749] 任意子数组和的绝对值的最大值
 */

// @lc code=start
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int result = 0;
        result = Math.max(result, run(nums));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = - nums[i];
        }
        result = Math.max(result, run(nums));
        return result;
    }

    private int run(int[] nums) {
        int result = 0;
        int preSum = 0;
        int min = 0;
        for (int x : nums) {
            int sum = preSum + x;
            result = Math.max(result, sum - min);
            preSum = sum;
            min = Math.min(min, sum);
        }
        return result;
    }
}
// @lc code=end

