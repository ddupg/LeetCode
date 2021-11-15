/*
 * @lc app=leetcode.cn id=268 lang=golang
 *
 * [268] 丢失的数字
 */
package leetcode;

// @lc code=start
func missingNumber(nums []int) int {
	n := len(nums);
	sum := 0;
	expect := n * (n + 1) / 2 - n;
	y := n;
	for _, x := range nums {
		sum = sum + x;
		if x < 0 || x >= n {
			y = x;
		}
	}
	return expect + y - sum;
}
// @lc code=end

