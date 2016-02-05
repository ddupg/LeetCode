public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        int x, y;
        if((n & 1) == 0){
            x = n/2;
            y = n/2+1;
        }else{
            x = y = n/2+1;
        }
        double ans=0;
        for (int i=1, j=0, k=0; i<=n; i++){
            int now;
            if ((j< nums1.length && k<nums2.length && nums1[j] <= nums2[k]) ||
                    k == nums2.length){
                now = nums1[j++];
            }else{
                now = nums2[k++];
            }
            if (i == x)
                ans += now;
            if (i == y)
                ans +=now;
        }
        return ans / 2;
    }
}