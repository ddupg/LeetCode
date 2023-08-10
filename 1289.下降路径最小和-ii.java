/*
 * @lc app=leetcode.cn id=1289 lang=java
 *
 * [1289] 下降路径最小和  II
 */

// @lc code=start
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        min = Math.min(min, dp[i-1][k]);
                    }
                }
                dp[i][j] = min + grid[i][j];
            }
        }
        int result = dp[n-1][0];
        System.out.println(result);
        for (int i = 0; i < n; i++) {
            result = Math.min(result, dp[n-1][i]);
        }
        return result;
    }
}
// @lc code=end

