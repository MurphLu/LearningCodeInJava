package org.algorithm.leetcode.daily;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;

public class LoadArrFromFile {
    public static void main(String[] args) throws IOException {
        new LoadArrFromFile().loadFile();
    }
    public static int[] loadFile() throws IOException {

        File file = new File("algorithm/a.txt");
        System.out.println(file.getAbsolutePath());
//        System.out.println(file.exists());
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
//        System.out.println(bfr.readLine());
        List<String> sList = new LinkedList<>();
        while (true) {
            String s = bfr.readLine();
//            System.out.println(s);
            if (s==null){
                break;
            }
            sList.add(s);
        }
        int[] arr = new int[sList.size()];
        for (int i = 0; i < sList.size(); i++) {
            arr[i] = Integer.parseInt(sList.get(i));
        }
        return arr;
    }
}
