/*
 * @lc app=leetcode.cn id=629 lang=golang
 *
 * [629] K个逆序对数组
 */

package main

// @lc code=start

var mod = 1000000007

func kInversePairs(n int, m int) int {
	dp := make([][]int, n+1)
	dp[0] = make([]int, m+2)
	add(dp[0][:], 0, 1)
	for i := 1; i <= n; i++ {
		dp[i] = make([]int, m+2)
		for j := 0; j <= m; j++ {
			//for k := 0; k <= j && k < i; k++ {
			//	dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % mod
			//}
			l := j - min(i-1, j)
			r := j
			add(dp[i][:], j, (mod-sum(dp[i-1][:], l-1)+sum(dp[i-1][:], r))%mod)
		}
	}
	return (mod - sum(dp[n][:], m-1) + sum(dp[n][:], m)) % mod
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func lowbit(x int) int {
	return x & -x
}

func add(arr []int, x int, y int) {
	x++
	for x < len(arr) {
		arr[x] = (arr[x] + y) % mod
		x = x + lowbit(x)
	}
}

func sum(arr []int, x int) int {
	x++
	var ans = 0
	for x >= 1 {
		ans = (ans + arr[x]) % mod
		x = x - lowbit(x)
	}
	return ans
}

// @lc code=end

func main() {
	println(kInversePairs(10, 6))
}
