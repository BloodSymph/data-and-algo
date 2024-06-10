package org.example.algo.recursive;

public final class Palindrome {

    public Palindrome() {
    }

    public boolean isPalindrome(String string, int i) {
        if (i == Math.round(string.length() / 2)) return true;
        if (string.charAt(i) != string.charAt(string.length() - 1 -i )) return false;
        return isPalindrome(string, ++i);
    }
}
