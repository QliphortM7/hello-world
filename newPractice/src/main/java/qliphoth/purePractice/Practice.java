package qliphoth.purePractice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    /**
     *  48.最长不含重复字符的子字符串
     *  很容易想到Fk定义为以k结尾的最长不重复字符字符串的长度
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

    public static void main(String[] args) {
        Practice pc = new Practice();
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("hahaha", "hollischuang");
        map.put("www","wwwww");
        map.put("rrrr","tttt");
        try {
            Class<?> mapType = map.getClass();
            Method capacity = mapType.getDeclaredMethod("capacity");
            capacity.setAccessible(true);
            System.out.println("capacity : " + capacity.invoke(map));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } finally {
            System.out.println("1");
        }

    }

}
