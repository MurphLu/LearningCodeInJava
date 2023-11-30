package org.algorithm.questions.findLow;

/**
 * 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装包装不可拆分。
 * 可是小虎现在只想购买恰好n个苹果，小虎想购买尽量少的袋数方便携带。
 * 如果不能购买恰好n个苹果，小虎将不会购买。
 * 输入一个整数n，表示小虎想购买的个苹果，返回最小使用多少袋子。
 * 如果无论如何都不能正好装下，返回-1。
 */
public class BuyApple {
    public static void main(String[] args) {

    }

    /**
     * 进一步优化，对数器，打点，找规律，生解
     * @param n
     * @param x
     * @param y
     * @return
     */
    public int buy(int n, int x, int y) {
        int b = Math.max(x, y);
        int s = Math.min(x, y);
        int bagB = n / b;
        int bagS = -1;
        int remain = n - bagB + b;
        while (bagB >= 0) {
            bagS = remain % s == 0 ? remain / s : -1;
            if (bagS != -1) {
                break;
            } else {
                remain = n - b * bagB--;
            }
        }
        return bagS == -1 ? -1 : (bagS + bagB);
    }
}
