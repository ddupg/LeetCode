/*
 * @lc app=leetcode.cn id=2678 lang=java
 *
 * [2678] 老人的数目
 */

// @lc code=start
class Solution {
    public int countSeniors(String[] details) {
        int cnt = 0;
        for (String s : details) {
            if (Integer.valueOf(s.substring(11, 13)) > 60) {
                ++ cnt;
            }
        }
        return cnt;
    }
}
// @lc code=end

