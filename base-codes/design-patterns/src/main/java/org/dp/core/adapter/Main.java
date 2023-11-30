package org.dp.core.adapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

// adapter  wrapper
// 常见的 Adapter类不一定是adapter 如 windowAdapter
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bf = new BufferedReader(isr);
    }
}
