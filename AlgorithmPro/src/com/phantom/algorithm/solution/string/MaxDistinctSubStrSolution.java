package com.phantom.algorithm.solution.string;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>无重复字符的最长子串</p>
 * 给定一个字符串 s ，找到 s 中不包含重复字符的<b>最长子串</b>的长度。
 * 示例 1 ：
 * 输入："pwwkew"
 * 输出："wke"
 * 示例 2 ：
 * 输入："bbbb"
 * 输出："b"
 */
public class MaxDistinctSubStrSolution {
    public static void main(String[] args) {
        String s = "pwwqveedwsx";
        System.out.println("字符串 " + s + " 的无重复字符的最长子串长度为：" + maxLength(s));
        System.out.println("2字符串 " + s + " 的无重复字符的最长子串长度为：" + maxLength2(s));
    }

    /**
     * 自己实现的版本：使用滑动窗口进行判断
     * @param s
     * @return
     */
    private static int maxLength(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        Set charSet = new HashSet();
        int len = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            if (charSet.contains(s.charAt(end))) {
                start = end + 1;
                len = Math.max(end - start + 1, len);
                charSet.clear();
            }
            charSet.add(s.charAt(end));
        }
        return Math.max(len, charSet.size());
    }

    /**
     * 力扣上给的实现版本
     */
    private static int maxLength2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        int i = 0, j = 0, result = 0;
        int n = s.length();
        Set set = new HashSet();
        while(i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                result = Math.max(result, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }

        return result;
    }
}
