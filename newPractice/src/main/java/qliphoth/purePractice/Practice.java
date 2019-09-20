package qliphoth.purePractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     * leetcode.17  电话号码的字母组合
     * 普通递归
     */
    public static String[] arr = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        getCombList(digits, "",list);
        return list;
    }

    private void getCombList(String digits, String a, List<String> list) {
        if(digits==null||digits.length()==0) {
            return;
        }
        int num = Integer.parseInt(digits.substring(0,1));
        char[] chars = arr[num].toCharArray();
        for(char c:chars) {
            String b = a + c;
            if(digits.length()==1) {
                list.add(b);
            } else {
                getCombList(digits.substring(1,digits.length()),b,list);
            }
        }
    }

    public static void main(String[] args) {
        Practice pc = new Practice();
        try (BufferedReader br =
                     new BufferedReader(new FileReader("name.txt"))) {
            try{
                BufferedReader br2 =
                        new BufferedReader(new FileReader("name1.txt"));
                System.out.println("catch0");
            } catch (IOException e) {
                System.out.println("catch1");
            }
        }catch (IOException e) {
            System.out.println("catch2");
        }
    }

}
