/*
 * @lc app=leetcode.cn id=397 lang=golang
 *
 * [397] 整数替换
 */

package main

// @lc code=start
func integerReplacement(n int) int {
	var queue []int64
	steps := make(map[int64]int)
	queue = append(queue, int64(n))
	steps[int64(n)] = 0
	for len(queue) > 0 {
		x := queue[0]
		if x == 1 {
			return steps[x]
		}
		queue = queue[1:]
		var nxt []int64
		if x & 1 == 0 {
			nxt = append(nxt, x / 2)
		} else {
			nxt = append(nxt, x + 1, x - 1)
		}
		for _, y := range nxt {
			_, ok := steps[y]
			if !ok {
				steps[y] = steps[x] + 1
				queue = append(queue, y)
			}
		}
	}
	return 0
}
// @lc code=end

func main() {
	println(integerReplacement(1111111111))
}