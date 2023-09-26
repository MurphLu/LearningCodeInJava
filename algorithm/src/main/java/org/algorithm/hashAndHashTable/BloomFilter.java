package org.algorithm.hashAndHashTable;

/**
 * 布隆过滤器
 */
public class BloomFilter {
    public static void main(String[] args) {

    }

    /**
     * bit map
     */
    public void bitMap() {
        // bitmap
        int[] arr = new int[10]; // 32bit * 10 -> 320bit;

        int i = 178; // 获取第 178个 bit 的状态
        int numIdx = 178 / 32;
        int bitIdx = 178 % 32;

        // 获取 178 位的状态
        int s = ((arr[numIdx] >> (bitIdx)) & 1);

        // 将 178 位的状态改为 1
        arr[numIdx] = arr[numIdx] | (1<< bitIdx);

        // 将 178 位的状态改为 0
        arr[numIdx] = arr[numIdx] & (~(1<<bitIdx));

        // 获取第 178个 bit 的状态
        int bit = (arr[i/32] >>(i%32)) & 1;
    }

    // 无删除行为，只和样本量和失误率有关（样本量大，小失误）
    public void bloomFilter() {
        // m 长的一个 bit arr，所占空间位 m/8 字节，m 越大失误率越低
        // 将 url 分别调用 k 个哈希函数并将 k 个哈希值取模 m 后将结果位置为 1，加入黑名单
        // 判断的时候用 url 分别调用  k 个哈希函数并将 k 个哈希值取模 m 后，如果结果在 bit arr 上找结果位置都为 1，那么这个 url 在黑名单
        // 可能会有误报（白名单中的 url 误报为 黑名单）

        // 需要根据 m 和样本量来计算有多少哈希函数最合适

        // n = 样本量，p = 失误率
        // m = (n * lnP) / (ln2)² 向上取整
        // k = ln2 * (m/n) ≈ 0.7 * (m/n) 个 向上取整
        // p ≈ (1 - e^(-kn/m))^k
    }
}
