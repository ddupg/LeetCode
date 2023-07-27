/*
 * @lc app=leetcode.cn id=2500 lang=java
 *
 * [2500] 删除每行中的最大值
 */

// @lc code=start

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int deleteGreatestValue(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            Arrays.sort(grid[i]);
        }
        int sum = 0;
        for (int j = 0; j < grid[0].length; j++) {
            int max = grid[0][j];
            for (int i = 1; i < grid.length; i++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            sum += max;
        }
        return sum;
    }
}
// @lc code=end

