package pureTest;

import java.util.*;

public class Practice {

    public static int numIslands(char[][] grid) {
        if(grid==null||grid.length==0) {
            return 0;
        }
        int count = 0;
        int height = grid.length;
        int width = grid[0].length;
        int[][] flag = new int[grid.length][grid[0].length];
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                flag[i][j] = 0;
            }
        }
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                if(flag[i][j]==0&&grid[i][j]=='1') {
                    searchIsland(grid,flag,i,j);
                    count += 1;
                }
            }
        }
        return count;
    }

    private static void searchIsland(char[][] grid, int[][] flag, int i, int j) {
        if(i<0||i>=grid.length||j<0||j>=grid[0].length) {
            return;
        }
        if(flag[i][j]==1) {
            return;
        }
        flag[i][j] = 1;
        if(grid[i][j]=='1') {
            searchIsland(grid,flag,i+1,j);
            searchIsland(grid,flag,i,j+1);
            searchIsland(grid,flag,i-1,j);
            searchIsland(grid,flag,i,j-1);
        }
    }

    /**
     *  动态规划，最重要的是重新定义问题，找到Fk的定义与其递推表达式
     *  首先将数组排序，定义Fk为以第k个数字结尾的最长整除集合数字个数
     * @param nums
     * @return
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
     *  构造端点值-将问题转化为二分查找 （一种技巧）
     *  *如果船在承载力为K的条件下可以完成在D天内送达包裹的任务，那么任何承载力大于K的条件下依然也能完成任务
     * @param weights
     * @param D
     * @return
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

    public int numSimilarGroups(String[] A) {
        for(int i=0;i<A.length;i++) {
            Set<String> wordSet = new HashSet<>();
            for(int j=i;j<A.length;j++) {

            }
        }
        return 0;
    }

    public int wordLength(String word1, String word2) {
        char[] characters1 = word1.toCharArray();
        char[] characters2 = word2.toCharArray();
        int len = 0;
        for(int i=0;i<word1.length();i++) {
            if(characters1[i]-characters2[i]!=0){
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        Practice pc = new Practice();
        String[] array = {"tars","rats","arts","star"};
        for(int i=0;i<array.length;i++) {
            System.out.println(pc.wordLength(array[0],array[i]));
        }
    }

}
