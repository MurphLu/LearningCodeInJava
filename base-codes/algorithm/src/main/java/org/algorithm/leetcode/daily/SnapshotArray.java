package org.algorithm.leetcode.daily;


import java.util.*;

/**
 * 实现支持下列接口的「快照数组」- SnapshotArray：
 *
 * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
 * void set(index, val) - 会将指定索引 index 处的元素设置为 val。
 * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
 * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 */
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


    /**
     * map 中保存每个 index 的 snap 值，set 的时候更新
     * key: version
     * value: value for version
     */
    Map<Integer, Integer>[] arr;

    /**
     * 每个 index 设置过的 version，获取 snap 值的时候做查找
     */
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
        // 设置当前 snap id 下的值为 val，并向 versions 中添加版本号
        Map<Integer, Integer> versionedVal = arr[index];
        versionedVal.put(snapId, val);

        versions[index].add(snapId);

    }

    public int snap() {
        // 每执行一次快照 snapId + 1
        return snapId++;
    }

    public int get(int index, int snap_id) {

        Map<Integer, Integer> map = arr[index];
        List<Integer> versionList = versions[index];
        // 如果当前 index 的 versionList 为空，或者 snap_id 比该 index 的初始值还要小
        // 则说明当前 index 未设置过值，直接返回初始值 0
        if (versionList.size() == 0 || snap_id < versionList.get(0)) {
            return 0;
        }
        // 如果 snap_id 大于当前 index version 的最后一个值，那么直接返回最后一个 version 的值，
        // 当前 snap_id 之后并未对该 index 设置过值
        if (versionList.get(versionList.size() - 1) < snap_id){
            return map.get(versionList.get(versionList.size() - 1));
        }

        // 如果 map 中恰好有当前的 snap_id，则直接返回对应的值即可
        if (map.containsKey(snap_id)) {
            return map.get(snap_id);
        }

        // 通过二分查找，找到当前 index 所有 version 中最后一个小于 snap_id 的 version
        // 并返回 map 中对应的值
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
