/*
 * @lc app=leetcode.cn id=1218 lang=java
 *
 * [1218] 最长定差子序列
 */

// @lc code=start

import java.util.*;

class Solution {

  public int longestSubsequence(int[] arr, int difference) {
    Map<Integer, List<Integer>> vps = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      vps.computeIfAbsent(arr[i], k -> new LinkedList<Integer>()).add(i);
    }
    if (difference == 0) {
      OptionalInt max = vps.values().stream().mapToInt(List::size).max();
      return max.getAsInt();
    }
    int[] dp = new int[arr.length];
    Arrays.fill(dp, 1);
    for (int i = 0; i < arr.length; i++) {
      int curr = arr[i];
      int nxt = curr + difference;
      if (!vps.containsKey(nxt)) {
        continue;
      }
      Iterator<Integer> it = vps.get(nxt).iterator();
      while (it.hasNext()) {
        int j = it.next();
        if (j < i) {
          it.remove();
          continue;
        }
        dp[j] = Math.max(dp[j], dp[i] + 1);
      }
    }
    int result = 0;
    for (int x : dp) {
      result = Math.max(result, x);
    }
    return result;
  }
}
// @lc code=end

