public class Solution {
    public String longestPalindrome(String s) {
        boolean[][] d = new boolean[s.length()][s.length()];

        for (int i=0; i<s.length(); i++)
            d[i][i]=true;
        for (int i=0; i<s.length()-1; i++)
            if (s.charAt(i)==s.charAt(i+1))
                d[i][i+1]=true;

        for (int len = 3; len <= s.length(); len++){
            for (int i = s.length()-len, j=i+len-1; i>=0; i--, j--){
                d[i][j] = (d[i+1][j-1] && s.charAt(i)==s.charAt(j));
            }
        }
        int ans = 0;
        for (int i=0; i<s.length(); i++)
            for (int j=i; j<s.length(); j++)
                if (d[i][j]){
                    ans = Math.max(ans, j-i+1);
                }
        for (int i=0; i<s.length(); i++)
            for (int j=i; j<s.length(); j++){
                if (d[i][j] && j-i+1==ans){
                    return s.substring(i, j+1);
                }
            }
        return "";
    }
}