/*
 * @lc app=leetcode.cn id=563 lang=golang
 *
 * [563] 二叉树的坡度
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var rst = 0

func findTilt(root *TreeNode) int {
	rst = 0
	sum(root)
	return rst
}

func sum(rt *TreeNode) int {
	if nil == root {
		return 0
	}
	l := findTilt(root.Left)
	r := findTile(root.Right)
	if l > r {
		rst += r - l
	} else {
		rst += l - r
	}
	return l + r + rt.val
}

// @lc code=end

