/*
 * @lc app=leetcode.cn id=859 lang=golang
 *
 * [859] 亲密字符串
 */

package main

// @lc code=start
func buddyStrings(s string, goal string) bool {
	if len(s) != len(goal) {
		return false
	}
	cnt := make([]int, 26)
	var diff []int
	for i := range s {
		a, b := s[i], goal[i]
		if a == b {
			cnt[a - 'a'] ++
		} else {
			diff = append(diff, i)
		}
	}
	if len(diff) == 0 {
		for _, c := range cnt {
			if c >= 2 {
				return true
			}
		}
	} else if len(diff) == 2 {
		if s[diff[0]] == goal[diff[1]] && s[diff[1]] == goal[diff[0]] {
			return true
		}
	}
	return false
}
// @lc code=end

func main() {
	println(buddyStrings("ab", "ba"))
	println(buddyStrings("ab", "ab"))
	println(buddyStrings("aa", "aa"))
	println(buddyStrings("aaaaaaabc", "aaaaaaacb"))
}