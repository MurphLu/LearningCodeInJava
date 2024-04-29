package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 *
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class DiagonalSort {
    public static void main(String[] args) {
        int[][] ints = new DiagonalSort().diagonalSort(new int[][]{
                new int[]{3, 9},
                new int[]{2, 4},
                new int[]{1, 2},
                new int[]{9, 8},
                new int[]{7, 3},
        });
        System.out.println(Arrays.deepToString(ints));
    }
    public int[][] diagonalSort(int[][] mat) {
        int vt = mat.length;
        int ht = mat[0].length;
        int h = ht;
        int v = 0;
        while (v < vt) {
            int hs = h;
            int vs = v;
            List<Integer> list = new ArrayList<>();
            while (hs < ht && vs < vt ) {
                System.out.print(mat[vs][hs]);
                list.add(mat[vs++][hs++]);
            }
            System.out.println();
            list.sort(Integer::compareTo);
            hs = h;
            vs = v;
            int i = 0;
            while (hs < ht && vs < vt ) {
                mat[vs++][hs++] = list.get(i++);
            }
            if (h != 0) {
                h--;
            } else {
                v++;
            }
        }
       return mat;
    }
}
