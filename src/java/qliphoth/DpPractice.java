package qliphoth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  dynamic programming，1，找到所求问题的正确定义Fk（表达式）；2. 找到Fk与相邻项Fk-i的关系（递推式）
 *  动态规划可以用递归，可以用数组保存子问题值，可以用变量记录过程变化值
 */
public class DpPractice {

    /**
     *  leetCode.368
     *  首先将数组排序，定义Fk为以第k个数字结尾的最长整除集合数字个数
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> divisibleList = new ArrayList<>();
        if(nums==null||nums.length==0) {
            return divisibleList;
        }
        Arrays.sort(nums);
        int[] maxLenArray = new int[nums.length];
        Arrays.fill(maxLenArray, 1); // 以i结尾最长整除集合的最短长度为1
        maxLenArray[0] = 1;
        for(int i=1;i<nums.length;i++) {
            for(int j=i-1;j>=0;j--) {
                if(nums[i]%nums[j]==0) { // 从0-i全部遍历一遍
                    maxLenArray[i] = (maxLenArray[j] + 1)>maxLenArray[i]?(maxLenArray[j] + 1):maxLenArray[i];
                }
            }
        }
        int maxLen = 0;
        int maxIndex = 0;
        for(int i=0;i<maxLenArray.length;i++) {
            if(maxLenArray[i]>maxLen) {
                maxIndex = i;
                maxLen = maxLenArray[i];
            }
        }
        for(int i=maxIndex;maxLen>0;i--) {
            if(nums[maxIndex]%nums[i]==0&&maxLenArray[i]==maxLen) {
                divisibleList.add(nums[i]);
                maxLen--; // 按照maxLen大小依次递减判断
            }
        }
        Collections.reverse(divisibleList);
        return divisibleList;
    }


    /**
     * leetCode.322
     * 定义Fk为k的最小组合数
     */
    public static int coinChange(int[] coins, int amount) {
        if(coins==null||coins.length<=0||amount<=0) {
            return -1;
        }
//        int[] sumType = new int[amount+1];
        int[] minType = new int[amount+1];
        Arrays.fill(minType, Integer.MAX_VALUE>>1);
//        sumType[0] = 1; // 记录所有可能的组合数量
        minType[0] = 0; // 记录所有组合中最小的组合数
        for (int coin : coins) {
            for (int j = 1; j < minType.length; j++) {
//                sumType[j] = sumType[j] + (j - coin >= 0 ? sumType[j - coin] : 0);
                minType[j] = Math.min(minType[j], j - coin >= 0 ? minType[j - coin] + 1 : minType[j]);
            }
        }
        if(minType[amount]>=Integer.MAX_VALUE>>1) {
            return -1;
        } else {
            return minType[amount];
        }
    }

    /**
     * leetcode.213
     * 拆解成{0,n-1}和{1,n}两个问题分别求最大值
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if(nums==null||nums.length<=0) {
            return 0;
        }
        if(nums.length==1) {
            return nums[0];
        }
        int len = nums.length;
        int p1 = 1;
        int max1 = 0, max2 = 0;
        int tmp1 = 0, tmp2 = 0; // 使用tmp记录中间值
        int tmp3 = 0, tmp4 = 0;
        for(int i=0;i<len-1;i++) {
            max1 = Math.max(tmp1,tmp2+nums[i]);
            max2 = Math.max(tmp3,tmp4+nums[i+p1]);
            tmp2 = tmp1;
            tmp1 = max1;
            tmp4 = tmp3;
            tmp3 = max2;
        }
        return Math.max(max1, max2);
    }

    /**
     * leetcode.416
     * 分割成两个子集，使得两个子集的元素和相等 → 存在子集{a1,a2…ak}使得∑ai=∑Ai/2 → 定义Fi为以i结尾不大于∑Ai/2的最大值
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        if(nums==null||nums.length<=1) {
            return false;
        }
        int sum = 0;
        for(int ele : nums) {
            sum += ele;
        }
        if((sum&1)==1) {
            return false;
        }
        int targetSum = sum>>1;
        int[] arr = new int[targetSum+1];
        arr[0] = nums[0];
        for(int i=1;i<nums.length;i++) {

        }
        return false;
    }
}