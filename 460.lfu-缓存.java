/*
 * @lc app=leetcode.cn id=460 lang=java
 *
 * [460] LFU 缓存
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class LFUCache {

    private Map<Integer, Counter> kvs = new HashMap<>();
    private TreeSet<Counter> lru = new TreeSet<>();
    private int capacity;
    private int operation;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        operation = 0;
    }
    
    public int get(int key) {
        if (!kvs.containsKey(key)) {
            return -1;
        }
        Counter counter = kvs.get(key);
        lru.remove(counter);
        counter.inc();
        lru.add(counter);
        return counter.value;
    }
    
    public void put(int key, int value) {
        Counter counter = kvs.get(key);

        if (counter == null) {
            if (kvs.size() >= capacity) {
                Counter fst = lru.pollFirst();
                kvs.remove(fst.key);
            }
            counter = new Counter();
            counter.key = key;
            counter.value = value;
            counter.inc();
            kvs.put(key, counter);
            lru.add(counter);
        } else {
            lru.remove(counter);
            counter.value = value;
            counter.inc();
            lru.add(counter);
        }
    }

    class Counter implements Comparable<Counter> {
        int key;
        int value;
        int cnt;
        int ts;

        public int compareTo(Counter o) {
            int v = Integer.compare(this.cnt, o.cnt);
            if (v != 0) {
                return v;
            }
            return Integer.compare(this.ts, o.ts);
        }

        public void inc() {
            cnt ++;
            ts = LFUCache.this.operation ++;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

