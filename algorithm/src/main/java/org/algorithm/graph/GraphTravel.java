package org.algorithm.graph;

import org.algorithm.graph.graph.Graph;
import org.algorithm.graph.graph.Node;

import java.util.*;

public class GraphTravel {
    // 宽度优先遍历
    public static void bfs(Node node) {
        if (node == null) { return; }
        Queue<Node> q = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        q.add(node);
        set.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.println(cur.val);
            for (Node n : cur.nexts) {
                if (!set.contains(n)) {
                    q.add(n);
                    set.add(n);
                }
            }

        }
    }

    //深度优先
    public static void dfs(Node node) {
        if (node == null) { return; }
        Stack<Node> s = new Stack<>();
        Set<Node> set = new HashSet<>();
        s.push(node);
        set.add(node);
        System.out.println(node.val);
        while (!s.isEmpty()) {
            Node cur = s.pop();
            for (Node n : cur.nexts) {
                if (!set.contains(n)) {
                    s.push(cur);
                    s.push(n);
                    set.add(n);
                    System.out.println(n.val);
                    break;
                }
            }
        }
    }
}
