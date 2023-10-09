/*
 * @lc app=leetcode.cn id=2578 lang=java
 *
 * [2578] 最小和分割
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int splitNum(int num) {
        List<Integer> nums = new ArrayList<>();
        while (num > 0) {
            nums.add(num % 10);
            num /= 10;
        }
        if (nums.size() % 2 > 0) {
            nums.add(0);
        }
        Collections.sort(nums, (a, b) -> Integer.compare(b, a));
        int[] sum = new int[nums.size() / 2];
        for (int i = 0; i < nums.size(); i += 2) {
            sum[i / 2] = nums.get(i) + nums.get(i + 1);
        }
        int rst = 0;
        for (int i = sum.length - 1; i >= 0; i --) {
            rst *= 10;
            rst += sum[i];
        }
        return rst;
    }
}
// @lc code=end

