package qliphoth.purePractice;

import java.util.HashMap;
import java.util.Map;

public class stringPractice {

    public static int longestPalindrome(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int flag = 0;
        int maxLen = 0;
        int tmp = 0;
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            tmp = entry.getValue();
            if((tmp&1)!=0) {
                flag = 1;
                maxLen += tmp-1;
            } else {
                maxLen += tmp;
            }
        }
        return maxLen + flag;
    }
}
