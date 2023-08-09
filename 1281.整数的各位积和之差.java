/*
 * @lc app=leetcode.cn id=1281 lang=java
 *
 * [1281] 整数的各位积和之差
 */

// @lc code=start
class Solution {
    public int subtractProductAndSum(int n) {
        int x = 1, s = 0;
        for (; n > 0; n /= 10) {
            int xx = n % 10;
            x *= xx;
            s += xx;
        }
        return x - s;
    }
}
// @lc code=end

