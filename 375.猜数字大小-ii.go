/*
 * @lc app=leetcode.cn id=375 lang=golang
 *
 * [375] 猜数字大小 II
 */

package main;

// @lc code=start

var dp [][]int

func getMoneyAmount(n int) int {
	dp = make([][]int, n + 1, n + 1)
	for i := 1; i < len(dp); i++ {
		dp[i] = make([]int, n + 1, n + 1)
	}
	return Dp(1, n)
}

func Dp(i, j int) int {
	if i >= j {
		return 0
	}
	if dp[i][j] != 0 {
		return dp[i][j]
	}

	var tmp = j * j
	for k := i; k <= j; k++ {
		tmp = min(tmp, max(Dp(i, k - 1), Dp(k + 1, j)) + k)
	}
	dp[i][j] = tmp
	return dp[i][j]
}

func min(x, y int) int {
	if x < y {
		return x;
	}
	return y;
}

func max(x, y int) int {
	if x > y {
		return x;
	}
	return y;
}

// @lc code=end

func main() {
	println(getMoneyAmount(10))
	println(getMoneyAmount(1))
	println(getMoneyAmount(2))
	println(getMoneyAmount(25))
	println(getMoneyAmount(200))
}