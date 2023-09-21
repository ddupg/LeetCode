/*
 * @lc app=leetcode.cn id=2603 lang=java
 *
 * [2603] 收集树中金币
 */

// @lc code=start

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        int[] in = new int[n];
        Map<Integer, Set<Integer>> es = new HashMap<>();
        for (int[] e : edges) {
            es.computeIfAbsent(e[0], k -> new HashSet<Integer>()).add(e[1]);
            es.computeIfAbsent(e[1], k -> new HashSet<Integer>()).add(e[0]);
            ++ in[e[0]];
            ++ in[e[1]];
        }
    
        clean(coins, es, in);

        cleanLeft(es, in);

        int rst = 0;
        for (Integer x : es.keySet()) {
            rst += es.get(x).size();
        }
        return rst;
    }

    private void bfs(Map<Integer, Set<Integer>> es, int[] in, Queue<Integer> q, Predicate<Integer> check, IntConsumer action) {
        while(!q.isEmpty()) {
            Integer x = q.poll();
            in[x] --;
            Set<Integer> nxt = es.remove(x);
            for (Integer y : nxt) {
                in[y] --;
                es.get(y).remove(x);
                if (in[y] == 1 && check.test(y)) {
                    action.accept(y);
                }
            }
        }
    }

    private void findLeft(int[] in, Predicate<Integer> check, IntConsumer action) {
        for (int i = 0; i < in.length; i ++) {
            if (in[i] == 1 && check.test(i)) {
                action.accept(i);
            }
        }
    }

    private void clean(int[] coins, Map<Integer, Set<Integer>> es, int[] in) {
        Queue<Integer> q = new LinkedList<>();
        findLeft(in, x -> coins[x] != 1, q::add);
        bfs(es, in, q, x -> coins[x] != 1, q::add);
    }

    private void cleanLeft(Map<Integer, Set<Integer>> es, int[] in) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        findLeft(in, x -> true, q1::add);
        bfs(es, in, q1, x -> true, q2::add);
        bfs(es, in, q2, x -> true, x -> {});
    }
}
// @lc code=end

