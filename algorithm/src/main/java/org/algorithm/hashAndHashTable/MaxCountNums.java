package org.algorithm.hashAndHashTable;


/**
 * 40 亿个 0~42亿的数，求出现次数最多的数
 * 1G 内存
 * 如果直接利用 hashTable 并且忽略 hashTable自身结构的内存占用的话，最坏情况下 HashTable<int, int> 40亿 * (4B + 4B) == 320亿 = 32G

 * 利用 hash 的均匀性，hash % 100 后的值理论上分布也是均匀的
 * 将 40亿个数 hash 之后 %00，根据结果将其分配到 0~99 号文件中，理论上 0~99 号文件中的数字个数基本一样
 * 然后再对 0~99 号文件分别用 hashTale 计算数字出现个数
 */
// 哈希的均匀性
public class MaxCountNums {
}
