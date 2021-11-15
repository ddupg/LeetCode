/*
 * @lc app=leetcode.cn id=299 lang=golang
 *
 * [299] 猜数字游戏
 */

// @lc code=start
func getHint(secret string, guess string) string {
	a := 0;
	b := 0;
	ma := make(map[byte]int);
	mb := make(map[byte]int);
	l := len(secret)
	for i := 0; i < l; i++ {
		if secret[i] == guess[i] {
			a ++;
		} else {
			c, ok := ma[secret[i]]
			if (ok) {
				ma[secret[i]] = c + 1;
			} else {
				ma[secret[i]] = 1;
			}
			c, ok = mb[guess[i]]
			if (ok) {
				mb[guess[i]] = c + 1;
			} else {
				mb[guess[i]] = 1;
			}
		}
	}
	for x := range ma {
		c, ok := mb[x];
		if (ok) {
			b += Min(c, ma[x])
		}
	}
	return fmt.Sprintf("%dA%dB", a, b);
}

func Min(x, y int) int {
	if x < y {
			return x
	}
	return y
}
// @lc code=end

