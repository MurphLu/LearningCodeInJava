package org.algorithm;

import org.algorithm.sort.InsertSort;
import org.algorithm.sort.MergeSort;
import org.algorithm.sort.QuickSort;
import org.algorithm.utils.ArrayUtils;
import org.junit.Test;
import org.algorithm.sort.Sorter;
import java.util.Arrays;

public class SortTest {
    static int[][] cases;
    static {
        cases = ArrayUtils.generate(100, 1000, 100);
    }

    @Test
    public void testQuick() throws Exception {
        testSort(new QuickSort());
    }

    @Test
    public void testInsert() throws Exception {
        testSort(new InsertSort());
    }

    @Test
    public void testMerge() throws Exception {
        testSort(new MergeSort());
    }

    private void testSort(Sorter sorter) throws Exception {
        for (int i = 0; i < cases.length; i++) {
            int[] arr = cases[i];
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();

            sorter.sort(arr1);
            Arrays.sort(arr2);
            System.out.println(Arrays.toString(arr1));
            System.out.println(Arrays.toString(arr2));
            if(!Arrays.equals(arr1, arr2)){
                System.out.println("error...");
                throw new Exception(
                                "\n==================================================================" +
                                "\n=========== error... " +
                                "\n=========== passed: " + i + 1 + " in " + cases.length + " cases" +
                                "\n==================================================================");
            }
            System.out.println("passed " + (i + 1) + " in " + cases.length + "------");
        }
        System.out.println("all passed!!!!! ");
    }
}

