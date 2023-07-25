/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true;
            }
        }
        if (p.length() >=2 && p.charAt(1) == '*') {
            dp[0][2] = true;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                char x = s.charAt(i), y = p.charAt(j);
                if (dp[i][j]) {
                    if (match(x, y)) {
                        dp[i + 1][j + 1] = true;
                    }
                }
                if (y == '*') {
                    if (dp[i][j + 1] && match(x, p.charAt(j - 1))) {
                        dp[i + 1][j + 1] = true;
                    }
                    if (dp[i][j] && match(x, p.charAt(j - 1))) {
                        dp[i + 1][j + 1] = true;
                    }
                    if (j > 0 && dp[i + 1][j - 1]) {
                        dp[i + 1][j + 1] = true;
                    }
                }
                // if (dp[i + 1][j + 1]) {
                    // System.out.println(i + " " + j);
                // }
            }
        }
        return dp[s.length()][p.length()];
    }

    private boolean match(char x, char y) {
        return x == y || y == '.';
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab", "c*a*b")); // true
        System.out.println(new Solution().isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(new Solution().isMatch("ab", ".*")); // true
        System.out.println(new Solution().isMatch("aaa", ".*")); // true
        System.out.println(new Solution().isMatch("a", "aa")); // false
        System.out.println(new Solution().isMatch("aaa", "ab*a")); // false
        System.out.println(new Solution().isMatch("aaa", "ab*a*c*a")); // true
        System.out.println(new Solution().isMatch("aaabaaaababcbccbaab", "c*c*.*c*a*..*c*")); // true
    }
}
// @lc code=end

