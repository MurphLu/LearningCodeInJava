package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 视为文件/目录名称。
 *
 * 请注意，返回的 规范路径 必须遵循下述格式：
 *
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径
 */
public class SimplifyPath {
    public static void main(String[] args) {
        new SimplifyPath().simplifyPath("/../");
    }
    public String simplifyPath(String path) {
        int i = path.length() - 1;
        while (i >= 0) {
            int temp = i;
            if(path.charAt(i) == '/') {
                while (temp >= 1 && path.charAt(temp-1) == '/') {
                    temp--;
                }
                path = path.substring(0, temp) + "/" + path.substring(i+1);

            }
            i = temp == i ? temp - 1 : temp;
        }
        if (path.charAt(path.length() - 1)  == '/') {
            path = path.substring(0, path.length() - 1);
        }
        List<String> pathList = new ArrayList<>();
        while (!path.isEmpty()) {
            while (path.charAt(0) == '/') {
                path = path.substring(1);
            }
            int idx = 0;
            while (idx < path.length() && path.charAt(idx) != '/') {
                idx++;
            }
            String curPath = path.substring(0, idx);
            if (curPath.equals("..")) {
                if(!pathList.isEmpty()) pathList.remove(pathList.size() - 1);
            } else if (!curPath.equals(".")) {
                pathList.add(curPath);
            }
            path = path.substring(idx);
        }

        StringBuilder sb = new StringBuilder();
        for(String s: pathList) {
            sb.append("/");
            sb.append(s);
        }
        String result = sb.toString();
        return result.isEmpty() ? "/" : result;
    }
}
