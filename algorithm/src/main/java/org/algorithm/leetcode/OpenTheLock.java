package org.algorithm.leetcode;

import java.util.*;

public class OpenTheLock {
    public static void main(String[] args) {
        String[] deadEnds = new String[]{"0201","0101","0102","1212","2002"};
        String target = "0202";

        System.out.println(new OpenTheLock().openLock(deadEnds, target));
    }
    public int openLock(String[] deadends, String target) {
        return process(deadends, target, 0);

    }

    public int process(String[] deadends, String target, int step){
        Set<String> deadEndsSet = new HashSet<>(List.of(deadends));
        if (deadEndsSet.contains("0000")) {
            return -1;
        }
        if(target.equals("0000")) {
            return 0;
        }

        LinkedList<String> currents = new LinkedList<>();

        currents.add("0000");
        deadEndsSet.add("0000");
        while (currents.size() > 0) {
            step ++;
            String end = currents.getLast();
            String head = "";
            while (!head.equals(end) && currents.size() > 0) {
                head = currents.poll();
                char[] cur = head.toCharArray();
                for (int i = 0; i < 4; i++) {
                    char[] down = cur.clone();
                    if (down[i] == '0') {
                        down[i] = '9';
                    } else {
                        down[i] -= 1;
                    }

                    char[] up = cur.clone();
                    if (up[i] == '9') {
                        up[i] = '0';
                    } else {
                        up[i] += 1;
                    }

                    String miS = new String(down);
                    String upS = new String(up);
                    if (miS.equals(target) || upS.equals(target)) {
                        return step;
                    }

                    if (!deadEndsSet.contains(miS)) {
                        currents.add(miS);
                        deadEndsSet.add(miS);
                    }
                    if (!deadEndsSet.contains(upS)) {
                        currents.add(upS);
                        deadEndsSet.add(upS);
                    }
                }
            }
        }
        return -1;
    }
}
