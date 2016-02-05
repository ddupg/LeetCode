class Solution(object):
    def isMatch(self, s, p):
        m = re.match(p, s)
        if m:
            return m and m.group() == s
        else:
            return False