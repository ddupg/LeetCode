/*
 * @lc app=leetcode.cn id=391 lang=golang
 *
 * [391] 完美矩形
 */

package main

// @lc code=start

import (
	"sort"
)

type SegmentTree struct {
	flag []bool
	val  []int
}

func lson(rt int) int {
	return rt<<1 | 1
}

func rson(rt int) int {
	return (rt + 1) << 1
}

func newSegmentTree(n int) *SegmentTree {
	return &SegmentTree{
		flag: make([]bool, (n<<2)+10),
		val:  make([]int, (n<<2)+10),
	}
}

func (t *SegmentTree) pushUp(rt int, l, r int) {
	if t.flag[lson(rt)] && t.flag[rson(rt)] && t.val[lson(rt)] == t.val[rson(rt)] {
		t.val[rt] = t.val[lson(rt)]
		t.flag[rt] = true
	} else {
		t.flag[rt] = false
	}
}

func (t *SegmentTree) pushDown(rt int) {
	if t.flag[rt] {
		t.val[lson(rt)] = t.val[rt]
		t.val[rson(rt)] = t.val[rt]
		t.flag[lson(rt)] = true
		t.flag[rson(rt)] = true
	}
}

func (t *SegmentTree) build(l, r, rt, val int) {
	if l == r {
		t.flag[rt] = true
		t.val[rt] = val
		return
	}
	m := (l + r) >> 1
	t.build(l, m, lson(rt), val)
	t.build(m+1, r, rson(rt), val)
	t.pushUp(rt, l, r)
}

func (t *SegmentTree) update(x, y, val, l, r, rt int) {
	if x <= l && r <= y {
		t.val[rt] = val
		t.flag[rt] = true
		return
	}
	t.pushDown(rt)
	m := (l + r) >> 1
	if m >= x {
		t.update(x, y, val, l, m, lson(rt))
	}
	if m < y {
		t.update(x, y, val, m+1, r, rson(rt))
	}
	t.pushUp(rt, l, r)
}

func (t *SegmentTree) query(x, y, val, l, r, rt int) bool {
	if x <= l && y >= r {
		return t.flag[rt] && t.val[rt] == val
	}
	t.pushDown(rt)
	m := (l + r) >> 1
	if m >= x {
		if !t.query(x, y, val, l, m, lson(rt)) {
			return false
		}
	}
	if m < y {
		if !t.query(x, y, val, m+1, r, rson(rt)) {
			return false
		}
	}
	return true
}

func isRectangleCover(rectangles [][]int) bool {
	ymap := []int{}
	for _, r := range rectangles {
		ymap = append(ymap, r[1], r[3])
	}
	ymap = sortAndUnique(ymap)
	n := len(ymap) - 2

	sort.Slice(rectangles, func(i, j int) bool {
		return rectangles[i][0] < rectangles[j][0]
	})

	tree := newSegmentTree(n)
	tree.build(0, n, 0, rectangles[0][0])

	maxx := rectangles[0][0]
	for _, r := range rectangles {
		x1, y1, x2, y2 := r[0], sort.SearchInts(ymap, r[1]), r[2], sort.SearchInts(ymap, r[3])-1
		if tree.query(y1, y2, x1, 0, n, 0) {
			tree.update(y1, y2, x2, 0, n, 0)
		} else {
			return false
		}
		if x2 > maxx {
			maxx = x2
		}
	}

	return tree.query(0, n, maxx, 0, n, 0)
}

func sortAndUnique(arr []int) []int {
	sort.Ints(arr)
	j := 0
	length := len(arr)
	for i := 1; i < length; i++ {
		if arr[i] != arr[j] {
			arr[j+1] = arr[i]
			j++
		}
	}
	return arr[:j+1]
}

// @lc code=end

func main() {
	//println(isRectangleCover([][]int{
	//	{4,3,2,1},
	//}))
	//fmt.Println(isRectangleCover([][]int{
	//	{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4},
	//}))
	//fmt.Println(isRectangleCover([][]int{
	//	{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4},
	//}))

}
