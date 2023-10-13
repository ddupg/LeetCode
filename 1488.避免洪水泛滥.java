/*
 * @lc app=leetcode.cn id=1488 lang=java
 *
 * [1488] 避免洪水泛滥
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> rst = new HashMap<>();
        TreeSet<Integer> sun = new TreeSet<>();
        Map<Integer, Integer> rain = new HashMap<>();
        for (int i = 0; i < rains.length; i ++) {
            int r = rains[i];
            if (r == 0) {
                sun.add(i);
                continue;
            }
            if (rain.containsKey(r)) {
                Integer s = sun.higher(rain.get(r));
                if (s == null) {
                    return new int[0];
                }
                sun.remove(s);
                rst.put(s, r);
                rain.remove(r);
            }
            rain.put(r, i);
        }
        int[] result = new int[rains.length];
        for (int i = 0; i < result.length; i ++) {
            if (rains[i] == 0) {
                result[i] = rst.getOrDefault(i, 1);
            } else {
                result[i] = -1;
            }
        }
        return result;
    }
}
// @lc code=end

