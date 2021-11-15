/*
 * @lc app=leetcode.cn id=495 lang=golang
 *
 * [495] 提莫攻击
 */

// @lc code=start
func findPoisonedDuration(timeSeries []int, duration int) int {
	result := 0
	last := 0
	for _, time := range timeSeries {
		if time > last {
			result += duration
		} else {
			result += time + duration - last
		}
		last = time + duration
	}
	return result
}

// @lc code=end

