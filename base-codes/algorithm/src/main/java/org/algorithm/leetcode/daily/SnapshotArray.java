package org.algorithm.leetcode.daily;


import java.util.*;

public class SnapshotArray {
    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(2);

        snapshotArray.snap();
        System.out.println(snapshotArray.get(1, 0));
        System.out.println(snapshotArray.get(0, 0));
        snapshotArray.set(1,8);
        System.out.println(snapshotArray.get(1, 0));
        snapshotArray.set(0,20);
        System.out.println(snapshotArray.get(0, 0));
        snapshotArray.set(0,7);

        snapshotArray.snap();
        snapshotArray.snap();
    }


    Map<Integer, Integer>[] arr;
    List<Integer>[] versions;

    int snapId;

    public SnapshotArray(int length) {
        arr = new HashMap[length];
        versions = new ArrayList[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new HashMap<>();
            versions[i] = new ArrayList<>();
        }
    }

    public void set(int index, int val) {
        Map<Integer, Integer> versionedVal = arr[index];

        versionedVal.put(snapId, val);

        versions[index].add(snapId);

    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        Map<Integer, Integer> map = arr[index];
        List<Integer> versionList = versions[index];
        if (versionList.size() == 0 || snap_id < versionList.get(0)) {
            return 0;
        }
        if (versionList.get(versionList.size() - 1) < snap_id){
            return map.get(versionList.get(versionList.size() - 1));
        }
        if (map.containsKey(snap_id)) {
            return map.get(snap_id);
        }

        int left = 0;
        int right = versionList.size() - 1;
        int mid = right / 2;
        while (left < right-1) {
            if (versionList.get(mid) > snap_id) {
                right = mid;
            } else {
                left = mid;
            }
            mid = (right - left) / 2 + left;
        }
        return map.get(versionList.get(left));
    }
}
