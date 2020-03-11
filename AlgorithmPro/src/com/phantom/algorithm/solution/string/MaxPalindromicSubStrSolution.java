package com.phantom.algorithm.solution.string;

/**
 * <p>最长回文子串</p>
 * 给定一个字符串 s ，找到 s 中最长的回文子串。可以假设 s 的最大长度为 1000。
 * 示例 1 ：
 * 输入："babad"
 * 输出："bab" 及 "aba"
 * 示例 2 ：
 * 输入："cbbd"
 * 输出："bb"
 */
public class MaxPalindromicSubStrSolution {
    public static void main(String[] args) {
        String s = " ";
        System.out.println("字符串 " + s + " 的最大回文子串是：" + longestPalindrome(s));
    }

    private static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return "";
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCore(s, i, i);
            int len2 = expandAroundCore(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandAroundCore(String s, int left, int right) {
        while ((left >= 0) && (right < s.length()) && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
        }
        return right - left - 1;
    }

}
