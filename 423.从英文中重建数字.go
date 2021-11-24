/*
 * @lc app=leetcode.cn id=423 lang=golang
 *
 * [423] 从英文中重建数字
 */

package main

// @lc code=start
import (
	"bytes"
	"strings"
)

func originalDigits(s string) string {
	nums := []int{0, 2, 6, 8, 3, 4, 5, 1, 7, 9}
	chars := []string{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}
	numstrs := []string{"zero", "two", "six", "eight", "three", "four", "five", "one", "seven", "nine"}
	cnts := make(map[int32]int)
	result := make([]int, 10)
	for _, c := range s {
		_, ok := cnts[c]
		if !ok {
			cnts[c] = 0
		}
		cnts[c]++
	}
	for i, num := range nums {
		numstr := numstrs[i]
		cnt := len(s)
		for _, c := range numstr {
			if cnt > cnts[c] {
				cnt = cnts[c]
			}
		}
		result[num] = cnt
		for _, c := range numstr {
			cnts[c] -= cnt
		}
	}
	buffer := bytes.NewBufferString("")
	for i := 0; i < 10; i++ {
		buffer.WriteString(strings.Repeat(chars[i], result[i]))
	}
	return buffer.String()
}

// @lc code=end

func main() {
	println(originalDigits("owoztneoer"))
	println(originalDigits("fviefuro"))
	println(originalDigits("zeroonetwothreefourfivesixseveneightnine")) // "0123456789"
}
