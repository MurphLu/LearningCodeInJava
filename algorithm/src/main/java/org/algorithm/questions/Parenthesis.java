package org.algorithm.questions;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {
    public static void main(String[] args) {
        new Parenthesis().generateParenthesis(3);
    }
    public List<String> generateParenthesis(int n) {
        int left = n;
        int right = n;
        List<String> result = new ArrayList<>();
        process("", left, right, result);
        System.out.println(result);
        return result;
    }

    public void process(String res, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(res);
        }
        if (left > 0) {
            process(res + "(", left - 1, right, result);
        }
        if (left < right) {
            process(res + ")", left, right - 1, result);
        }
    }
}
