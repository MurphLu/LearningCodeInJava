package org.algorithm.graph.question;

import org.algorithm.graph.graph.Graph;
import org.algorithm.graph.graph.Node;

import java.util.*;

// 拓扑排序，要求有向图，有入度为 0 的节点，且无环
public class TopologicalSort {
    public static void main(String[] args) {

    }

    public static List<Node> sort(Graph graph) {
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQ = new LinkedList<>();
        for (Node node: graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQ.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQ.isEmpty()) {
            Node cur = zeroInQ.poll();
            result.add(cur);
            for (Node node: cur.nexts) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) == 0) {
                    zeroInQ.add(node);
                }
            }
        }
        return result;
    }
}
