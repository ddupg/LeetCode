/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU 缓存
 */

import java.util.HashMap;
import java.util.Map;

// @lc code=start
class LRUCache {

  private Node head, tail;
  private int capacity;

  private Map<Integer, Node> kvs;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    kvs = new HashMap<>(capacity);
  }

  public int get(int key) {
    Node node = kvs.get(key);
    if (node == null) {
      return -1;
    }
    remove(node);
    putHead(node);
    return node.value;
  }

  public void put(int key, int value) {
    Node node = kvs.get(key);
    if (node == null && kvs.size() >= capacity) {
      Node t = removeTail();
      kvs.remove(t.key);
    }
    if (node == null) {
      node = new Node();
      node.key = key;
    } else {
      remove(node);
    }
    node.value = value;
    putHead(node);
    kvs.put(key, node);
  }

  private void putHead(Node node) {
    node.pre = node.nxt = null;
    if (head == null) {
      head = tail = node;
      return;
    }
    node.nxt = head;
    head.pre = node;
    head = node;
  }

  private Node removeTail() {
    Node r = tail;
    tail = r.pre;
    r.pre = null;
    if (tail != null) {
      tail.nxt = null;
    }
    if (r == head) {
      head = null;
    }
    return r;
  }

  private void remove(Node node) {
    if (node == head) {
      head = node.nxt;
    }
    if (node == tail) {
      tail = node.pre;
    }
    if (node.nxt != null) {
      node.nxt.pre = node.pre;
    }
    if (node.pre != null) {
      node.pre.nxt = node.nxt;
    }
    node.pre = node.nxt = null;
  }

  class Node {
    Node pre, nxt;
    Integer key, value;
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end