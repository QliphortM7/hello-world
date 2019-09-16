package qliphoth;

import java.util.*;

/**
 *  dynamic programming，1，找到所求问题的正确定义Fk（表达式）；2. 找到Fk与相邻项Fk-i的关系（递推式）
 *  动态规划可以用递归，可以用数组保存子问题值，可以用变量记录过程变化值
 */
public class DpPractice {

    /**
     *  leetCode.368
     *  首先将数组排序，定义F(n)为以第n个数字结尾的最长整除集合数字个数
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
                    maxLenArray[i] = Math.max((maxLenArray[j] + 1), maxLenArray[i]);
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
     * leetCode.322 ∈0-1背包
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
     * leetcode.213 ∈斐波那契数列
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
     * leetcode.416 ∈0-1背包
     * 分割成两个子集，使得两个子集的元素和相等 → 存在子集{a1,a2…ak}使得∑ai=∑Ai/2
     * → 定义Fi为以i结尾不大于∑Ai/2的最大值 → 0-1背包问题
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
        for(int i=1;i<nums.length;i++) {
            for(int j=targetSum;j>=nums[i];j--) {
                arr[j] = Math.max(arr[j], arr[j-nums[i]]+nums[i]);
            }
        }
        return arr[targetSum] == targetSum;
    }

    /**
     * ∈0-1背包 小于n组合的最大值 → 逆序求max；小于n最大值的最小组合 → 顺序求min
     * @param n
     * @return
     */
    public static int numBox(int n) {
        int[] box = {4,9};
        int[] arr = new int[n+1];
        int[] min = new int[n+1];
        Arrays.fill(arr,0);
        Arrays.fill(min,n);
        for(int i=0;i<4;i++) {
            arr[i] = 0;
            min[i] = 0;
        }
        for (int value : box) {
            for (int i = n; i >= value; i--) {
                arr[i] = Math.max(arr[i], arr[i - value] + value);
                min[value+n-i] = Math.min(min[value+n-i], min[n-i] + 1);
            }
        }
        if(arr[n]==n) {
            return min[n];
        } else {
            return -1;
        }
    }

    /**
     * leetcode.219 完全平方数
     * 依旧是0-1背包动态规划 → 求小于n最大值的最小组合
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int[] arr = new int[n+1];
        Arrays.fill(arr, n);
        arr[0] = 0;
        for(int i=1;i*i<=n;i++) {
            int tmp = i*i;
            for(int j=tmp;j<=n;j++) {
                arr[j] = Math.min(arr[j-tmp]+1,arr[j]);
            }
        }
        return arr[n];
    }

    /**
     * leetcode.300 最长上升子序列
     * 定义 F(n)为以第n个数字结尾的最长递增子序列长度
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if(nums==null||nums.length<=0) {
            return 0;
        }
        if(nums.length==1) {
            return 1;
        }
        int[] arr = new int[nums.length];
        Arrays.fill(arr,1);
        int maxLen = 1;
        for(int i=1;i<arr.length;i++) {
            if(nums[i]>nums[i-1]) {
                int tmp = 1;
                for(int j=i-1;j>=0;j--) { // 从0-i全部遍历一遍
                    if(nums[j]<nums[i]) {
                        tmp = Math.max(arr[j] + 1,tmp);
                    }
                }
                arr[i] = tmp;
                maxLen = Math.max(maxLen, arr[i]);
            } else {
                for(int j=i-1;j>=0;j--) {
                    if(nums[j]<nums[i]) {
                        arr[i] = Math.max(arr[j] + 1,arr[i]);
                        maxLen = Math.max(maxLen, arr[i]);
                        break;
                    }
                }
            }
        }
        return maxLen;
    }

    /**
     *  48.最长不含重复字符的子字符串
     *  很容易想到F(n)定义为以n结尾的最长不重复字符字符串的长度
     * @param s
     * @return
     */
    public int maxSubString(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        char[] array = s.toCharArray();
        int[] maxLen = new int[array.length];
        int begin = 0;
        int end = 1;
        Map<Character,Integer> map = new HashMap<>(26);
        map.put(array[0],0);
        maxLen[0] = 1;
        for(int i=1;i<array.length;i++) {
            if(!map.containsKey(array[i])) {
                map.put(array[i],i);
                maxLen[i] = maxLen[i-1]+1;
            } else {
                maxLen[i] = Math.min(i - map.get(array[i]), maxLen[i-1]+1);
                map.put(array[i], i);
            }
        }
        return maxLen[array.length-1];
    }
}
