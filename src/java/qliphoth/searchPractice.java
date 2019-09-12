package qliphoth;

public class searchPractice {

    /**
     * leetcode.540有序数组中的单一元素
     * 可使用位运算，但二分查找可获得最短用时o(logn)，二分查找可视为寻找一个下界
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        if(nums==null||nums.length<=0) {
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        int mid;
        while(left<right) {
            mid = left + ((right-left)>>1);
            if((mid&1)==0&&nums[mid]==nums[mid+1]||(mid&1)==1&&nums[mid]==nums[mid-1]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }
}
