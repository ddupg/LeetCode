/*
 * @lc app=leetcode.cn id=901 lang=java
 *
 * [901] 股票价格跨度
 */

// @lc code=start

import java.util.Stack;

class StockSpanner {

    private Stack<Node> stack;
    private int idx;

    public StockSpanner() {
        stack = new Stack<>();
        idx = 0;
        stack.add(new Node(Integer.MAX_VALUE, idx++));
    }
    
    public int next(int price) {
        while (stack.peek().price <= price) {
            stack.pop();
        }
        int rst = idx - stack.peek().idx;
        stack.add(new Node(price, idx++));
        return rst;
    }

    class Node {
        int price;
        int idx;
        public Node(int price, int idx) {
            this.price = price;
            this.idx = idx;
        }
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
// @lc code=end

