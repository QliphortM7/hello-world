package qliphoth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  dynamic programming，1，找到所求问题的正确定义Fk（表达式）；2. 找到Fk与相邻项Fk-i的关系（递推式）
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
}
