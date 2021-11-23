/*
 * @lc app=leetcode.cn id=318 lang=golang
 *
 * [318] 最大单词长度乘积
 */

package main

// @lc code=start
func maxProduct(words []string) int {
	bits := make([]int64, len(words))
	lens := make([]int, len(words))
	for i, w := range words {
		lens[i] = len(w)
		bits[i] = 0
		for _, c := range w {
			bits[i] |= 1 << (c - 'a')
		}
	}
	l := len(words)
	rst := 0
	for i := 0; i < l; i++ {
		for j := i + 1; j < l; j++ {
			if bits[i] & bits[j] == 0 && lens[i] * lens[j] > rst {
				rst = lens[i] * lens[j]
			}
		}
	}
	return rst
}
// @lc code=end

func main() {
	println(maxProduct([]string{"abcw","baz","foo","bar","xtfn","abcdef"}))
}