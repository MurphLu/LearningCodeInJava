package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 */
public class RestoreIpAddresses {
    public static void main(String[] args) {
        RestoreIpAddresses ria = new RestoreIpAddresses();
        ria.restoreIpAddresses("101023");
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> rec = new ArrayList<>();
        process(s, "", 0, rec);
        System.out.println(rec);
        return rec;
    }

    private void process(String s, String cur, int part, List<String> record) {
        if (s.isEmpty() && part == 4) {
            record.add(cur);
        }
        if (part > 4 || s.isEmpty()) {
            return;
        }

        if (!cur.isEmpty()) {
            cur += ".";
        }
        if(s.charAt(0) == '0') {
            process(s.substring(1), cur + "0", part + 1, record);
        } else {
            int i = 1;
            while (i <=3 && i <= s.length()) {
                String subS = s.substring(0, i);
                if(Integer.parseInt(subS) <= 255){
                    process(s.substring(i), cur + subS, part + 1, record);
                    i++;
                } else {
                    break;
                }
            }
        }

    }
}
