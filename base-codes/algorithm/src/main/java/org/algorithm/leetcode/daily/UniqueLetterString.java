package org.algorithm.leetcode.daily;

import java.util.*;

public class UniqueLetterString {
    public static void main(String[] args) {
        UniqueLetterString uls = new UniqueLetterString();
        String s = "DELQGVWNZKIJJPSXOVWWIZUXCEGWSQLESNSRBMKZARFPAXSVWQEZDENDAHNNIBHGHTFDLPGDLFXMIYRFNLMXHNPIFUAXINXPXLCTTJNLGGMKJIOEWBECNOFQPVCIKIAZMNGHEHFMCPWSMJTMGVSXTOGCGUYKFMNCGLCBRAFJLJVPIVDOLJBURULPGXBVDCEWXXXLTRMSHPKSPFDGNVOCZWDXJUWVNAREDOKTZMIUDKDQWWWSAEUUDBHMWZELOSBIHMAYJEMGZPMDOOGSCKLVHTGMETHUISCLJKDOQEWGVBULEMUXGTRKGXYFDIZTZWMLOFTCANBGUARNWQEQWGMIKMORVQUZANJNRNPMJWYLVHWKDFLDDBBMILAKGFROEQAMEVONUVHOHGPKLBPNYZFPLXNBCIFENCGIMIDCXIIQJWPVVCOCJTSKSHVMQJNLHSQTEZQTTMOXUSKBMUJEJDBJQNXECJGSZUDENJCPTTSREKHPRIISXMWBUGMTOVOTRKQCFSDOTEFPSVQINYLHXYVZTVAMWGPNKIDLOPGAMWSKDXEPLPPTKUHEKBQAWEBMORRZHBLOGIYLTPMUVBPGOOOIEBJEGTKQKOUURHSEJCMWMGHXYIAOGKJXFAMRLGTPNSLERNOHSDFSSFASUJTFHBDMGBQOKZRBRAZEQQVWFRNUNHBGKRFNBETEDJIWCTUBJDPFRRVNZENGRANELPHSDJLKVHWXAXUTMPWHUQPLTLYQAATEFXHZARFAUDLIUDEHEGGNIYICVARQNRJJKQSLXKZZTFPVJMOXADCIGKUXCVMLPFJGVXMMBEKQXFNXNUWOHCSZSEZWZHDCXPGLROYPMUOBDFLQMTTERGSSGVGOURDWDSEXONCKWHDUOVDHDESNINELLCTURJHGCJWVIPNSISHRWTFSFNRAHJAJNNXKKEMESDWGIYIQQRLUUADAXOUEYURQRVZBCSHXXFLYWFHDZKPHAGYOCTYGZNPALAUZSTOU";
        System.out.println(uls.uniqueLetterString(s));
    }
    public int uniqueLetterString(String s) {
        return process(s, 0, s.length(), new HashSet<>(), new HashMap<>());
    }

    public int process(String s, int start, int end, Set<String> met, Map<String, Integer> dp) {
        String currentSub = start + "_" + end;

        if(met.contains(currentSub)) {
            return 0;
        }
        met.add(currentSub);
        if (start + 1 == end) {
            return 1;
        }
        String currentSubString = s.substring(start, end);
        int count = 0;
        if (dp.containsKey(currentSubString)) {
            count = dp.get(currentSubString);
        } else {
            Set<Character> cs = new HashSet<>();
            Set<Character> map = new HashSet<>();
            for (char c: currentSubString.toCharArray()) {
                if (map.contains(c)) {
                    cs.remove(c);
                } else {
                    map.add(c);
                    cs.add(c);
                }
            }
            count = cs.size();
        }
        dp.put(currentSubString, count);
        count += process(s, start + 1, end, met, dp);
        count += process(s, start, end - 1, met, dp);

        return count;
    }

    // 计算每个字符贡献多少
    private int process(String s) {
        // 记录每个字符出现的 Index list

        Map<Character, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(!indexMap.containsKey(c)) {
                indexMap.put(c, new ArrayList<>());
                indexMap.get(c).add(-1);
            }
            indexMap.get(c).add(i);
        }
        int res = 0;
        // 接下来计算每个字符作为能贡献的个数
        // 比如 ZABCD;
        // B 出现的 Index 为 2；
        // B 作为后缀能组成： ZAB, AB, B
        // B 作为前缀不包含B为： C, CD
        // 所以 B 之前包含B可以组成 三种，B 之后不包含B 能组成 两种，总共 6种
        for (Map.Entry<Character, List<Integer>> entry: indexMap.entrySet()) {
            List<Integer> indexes = entry.getValue();
            indexes.add(s.length());
            for (int i = 1; i < indexes.size() - 1; i++) {
                res += (indexes.get(i) - indexes.get(i - 1)) * (indexes.get(i + 1) - indexes.get(i));
            }
        }
        return res;
    }
}
