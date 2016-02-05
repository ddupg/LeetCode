public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int ans = 0;
        for (int i=0; i<s.length(); i++){
            set.clear();
            for (int j=i; j<s.length(); j++){
                if (set.contains(s.charAt(j))){
                    ans = Math.max(ans, j - i);
                    break;
                }
                set.add(s.charAt(j));
                ans = Math.max(ans, j-i+1);
            }
        }
        return ans;
    }
}