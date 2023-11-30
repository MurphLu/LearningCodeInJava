package org.algorithm.tree;

/**
 * 折纸游戏
 * 一条纸连续对折 N 此，打印折痕顺序
 *        凹
 *    凹       凸
 *  凹   凸   凹   凸
 */
public class PaperFolding {

    public static void main(String[] args) {
        printAllFolding(10);
    }

    public static void printAllFolding(int N) {
        printProcess(1, N, true);
    }

    public static void printProcess(int i, int N, boolean down) {
        if (i > N) { return; };
        printProcess(i+1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i+1, N, false);
    }
}
