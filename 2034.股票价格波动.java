/*
 * @lc app=leetcode.cn id=2034 lang=java
 *
 * [2034] 股票价格波动
 */

// @lc code=start

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class StockPrice {

    private Map<Integer, Node> map;
    private TreeSet<Node> set;
    private Node last;

    public StockPrice() {
        map = new HashMap<>();
        set = new TreeSet<>(Comparator.<Node>comparingInt(nd -> nd.price).thenComparingInt(nd -> nd.ts));
        last = null;
    }
    
    public void update(int timestamp, int price) {
        Node now = new Node(timestamp, price);
        Node pre = map.put(timestamp, now);
        if (pre != null) {
            set.remove(pre);
        }
        set.add(now);
        if (last == null || last.ts <= timestamp) {
            last = now;
        }
    }
    
    public int current() {
        return last.price;
    }
    
    public int maximum() {
        return set.last().price;
    }
    
    public int minimum() {
        return set.first().price;
    }

    class Node {
        int ts, price;
        public Node(int ts, int price) {
            this.ts = ts;
            this.price = price;
        }
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
// @lc code=end

