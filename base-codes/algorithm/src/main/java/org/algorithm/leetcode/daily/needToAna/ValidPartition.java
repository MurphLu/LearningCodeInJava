package org.algorithm.leetcode.daily.needToAna;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为一个或多个 连续 子数组。
 *
 * 如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
 *
 * 子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
 * 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
 * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
 * 如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
 */
public class ValidPartition {

    public static void main(String[] args) {
        System.out.println(new ValidPartition().validPartition(new int[]{7096,7096,7096,7096,7097,7098,7098,7098,7098,7098,7099,7100,7101,7102,7103,7104,7104,7105,7105,7105,7106,7106,7106,7107,7107,7107,7108,7109,7110,7111,7112,7113,7113,7113,7114,7114,7114,7115,7115,7115,7116,7116,7116,7116,7116,7116,7117,7117,7117,7118,7119,7120,7120,7120,7121,7122,7123,7124,7124,7125,7125,7125,7126,7126,7126,7126,7126,7127,7128,7129,7129,7130,7131,7131,7131,7131,7132,7133,7134,7134,7134,7135,7136,7137,7137,7138,7139,7140,7140,7140,7140,7140,7141,7142,7143,7143,7144,7145,7145,7145,7145,7146,7146,7147,7147,7147,7147,7147,7147,7148,7149,7149,7149,7150,7150,7150,7150,7150,7151,7151,7151,7151,7152,7153,7153,7154,7155,7156,7156,7156,7157,7158,7158,7158,7159,7160,7161,7162,7162,7163,7163,7163,7164,7165,7165,7166,7167,7167,7167,7168,7168,7168,7169,7169,7169,7169,7169,7169,7169,7170,7171,7171,7171,7171,7172,7173,7174,7174,7175,7176,7177,7178,7179,7180,7180,7181,7181,7181,7181,7181,7181,7181,7181,7182,7183,7183,7183,7183,7184,7184,7184,7184,7185,7186,7187,7187,7187,7188,7188,7188,7189,7190,7190,7191,7192,7193,7194,7195,7196,7196,7196,7196,7196,7197,7197,7197,7197,7197,7197,7197,7197,7198,7199,7199,7199,7199,7200,7200,7200,7200,7200,7201,7201,7201,7201,7201,7202,7203,7204,7204,7204,7205,7205,7206,7207,7208,7209,7210,7211,7211,7212,7213,7213,7214,7215,7215,7215,7215,7215,7215,7216,7216,7216,7217,7217,7218,7218,7218,7218,7218,7218,7219,7220,7221,7222,7222,7222,7222,7222,7222,7222,7222,7222,7222,7223,7224,7225,7225,7226,7226,7226,7227,7227,7227,7227,7227,7227,7228,7228,7228,7229,7230,7231,7231,7231,7231,7232,7233,7234,7235,7236,7237,7237,7237,7237,7237,7237,7237,7237,7238,7238,7238,7238,7238,7238,7238,7238,7239,7240,7241,7241,7241,7241,7242,7243,7244,7245,7245,7245,7245,7245,7245,7246,7247,7248,7248,7248,7248,7248,7249,7249,7249,7249,7249,7249,7249,7250,7251,7252,7253,7254,7255,7255,7255,7256,7256,7256,7256,7256,7256,7256,7256,7256,7256,7256,7257,7257,7257,7257,7257,7258,7258,7258,7259,7260,7260,7260,7260,7260,7260,7260,7261,7262,7262,7263,7264,7264,7264,7265,7265,7265,7265,7266,7267,7267,7267,7268,7268,7268,7269,7270,7271,7272,7272,7272,7273,7273,7273,7273,7274,7275,7275,7276,7277,7278,7278,7279,7279,7279,7280,7281,7281,7281,7281,7282,7283,7283,7283,7283,7284,7285,7286,7286,7286,7286,7286,7286,7286,7286,7286,7287,7287,7288,7289,7290,7290,7290,7290,7291,7291,7291,7291,7291,7291,7291,7292,7293,7294,7294,7294,7294,7294,7294,7295,7295,7295,7296,7297,7298,7298,7298,7298,7299,7299,7300,7300,7300,7300,7300,7301,7301,7301,7301,7301,7301,7301,7301,7302,7303,7304,7304,7304,7304,7304,7304,7305,7306,7306,7306,7307,7307,7307,7308,7309,7310,7311,7312,7313,7314,7314,7314,7314,7314,7314,7314,7314,7314,7314,7315,7316,7317,7318,7319,7320,7320,7320,7321,7321,7321,7321,7321,7321,7321,7321,7322,7323,7324,7324,7324,7324,7324,7324,7324,7325,7326,7326,7326,7326,7326,7326,7326,7326,7326,7327,7327,7327,7328,7328,7328,7328,7329,7330,7331,7331,7331,7331,7332,7333,7334,7334,7335,7336,7336,7336,7336,7337,7337,7338,7338,7338,7339,7340,7341,7341,7341,7341,7341,7342,7343,7343,7344,7345,7345,7346,7347,7348,7348,7348,7349,7349,7349,7350,7350,7350,7350,7351,7352,7352,7352,7352,7353,7354,7354,7355,7356,7357,7357,7358,7358,7358,7359,7360,7360,7360,7360,7360,7361,7362,7363,7363,7363,7363,7363,7363,7364,7364,7364,7365,7366,7367,7368,7368,7368,7369,7369,7369,7369,7369,7370,7370,7370,7370,7370,7370,7370,7371,7371,7371,7371,7372,7373,7373,7374,7375,7375,7375,7375,7375,7375,7375,7376,7376,7376,7376,7377,7378,7379,7379,7379,7380,7381,7382,7383,7383,7384,7385,7386,7387,7387,7388,7388,7389,7389,7389,7390,7391,7391,7392,7393,7394,7395,7396,7396,7397,7398,7398,7398,7398,7399,7399,7399,7400,7401,7401,7401,7401,7401,7401,7401,7401,7401,7401,7402,7403,7404,7405,7406,7407,7407,7408,7409,7410,7410,7410,7410,7411,7412,7413,7414,7414,7414,7414,7415,7416,7417,7417,7417,7418,7419,7420,7420,7421,7422,7422,7422,7422,7422,7422,7423,7424,7425,7425,7425,7425,7425,7426,7427,7427,7427,7427,7428,7428,7428,7429,7429,7429,7429,7430,7430,7430,7430,7431,7432,7433,7433,7433,7434,7435,7436,7436,7437,7438,7439,7439,7439,7440,7441,7441,7442,7443,7444,7444,7444,7444,7444,7444,7445,7446,7446,7446,7446,7447,7447,7447,7447,7447,7448,7448,7448,7448,7448,7448,7448,7448,7449,7450,7451,7452,7452,7452,7453,7453,7453,7453,7453,7453,7454,7455,7456,7456,7456,7456,7457,7458,7459,7460,7461,7462,7463,7463,7464,7464,7465,7466,7467,7467,7467}));
    }
    //4,4,4,5,6,7,7,7,7
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i < n; i++) {
            if (f[i - 1] // 且 i 之前的元素是否是有效的分割，如果是则继续判断，如果不是，那么直接跳过
                    && nums[i] == nums[i - 1] || // 是否两个元素相等，如果是，则当前位置与之前元素也可以组成有效的分割
                i > 1 && f[i - 2]  // 三个元素的情况
                        && (nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) || // 相等
                    nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2) { // 递增 1
                f[n + 1] = true;
            }
        }
        return f[n];
    }
}
