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

    /**
     *  leetcode.1011  在 D 天内送达包裹的能力
     *  构造端点值-将问题转化为二分查找 （一种技巧）
     *  *如果船在承载力为K的条件下可以完成在D天内送达包裹的任务，那么任何承载力大于K的条件下依然也能完成任务
     */
    public int shipWithinDays(int[] weights, int D) {
        if(weights==null||weights.length==0) {
            return 0;
        }
        int sum = 0;
        int maxNum = 0;
        for(int i:weights) {
            sum += i;
            if(i>maxNum) {
                maxNum = i;
            }
        }
        // 二分查找寻找一个上界：条件large>small, large = middle/ small = middle+1
        int mid = (maxNum + sum)>>1;
        while(sum>maxNum) {
            if(isAvailable(weights,mid,D)) {
                sum = mid;
            } else {
                maxNum = mid + 1;
            }
            mid = (maxNum + sum)>>1;
        }
        return mid;
    }

    private boolean isAvailable(int[] weights, int ship, int D) {
        int i = 0;
        int sum = 0;
        int count = 1;
        while (i<weights.length) {
            if(weights[i]>ship) {
                return false;
            }
            if((sum + weights[i])>ship) {
                count++;
                sum = weights[i];
            } else {
                sum += weights[i];
            }
            i++;
        }
        return count <= D;
    }
}
