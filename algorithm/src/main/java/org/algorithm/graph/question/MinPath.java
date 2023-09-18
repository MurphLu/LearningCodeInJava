package org.algorithm.graph.question;

import org.algorithm.graph.graph.Edge;
import org.algorithm.graph.graph.Graph;
import org.algorithm.graph.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dijkstra
 * 要求，不能有累加和为负数的环
 */
public class MinPath {
    public static void main(String[] args) {

    }

    public static Map<Node, Integer> dijkstra(Node head){
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectedNodes = new HashSet<>();
        distanceMap.put(head, 0);

        Node minNode = getDistanceAndUnselectNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distanceToMinNode = distanceMap.get(minNode);
            for (Edge e: minNode.edges) {
                Node nodeTo = e.to;
                if(!distanceMap.containsKey(nodeTo)) {
                    distanceMap.put(nodeTo, distanceToMinNode + e.weight);
                }
                distanceMap.put(nodeTo, Math.min(distanceMap.get(minNode), distanceToMinNode + e.weight));
            }
            selectedNodes.add(minNode);
            minNode = getDistanceAndUnselectNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getDistanceAndUnselectNode(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        Node node = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Node cur: distanceMap.keySet()) {
            Integer val = distanceMap.get(cur);
            if(!selectedNodes.contains(cur) && val < minDistance ) {
                minDistance = val;
                node = cur;
            }
        }
        selectedNodes.add(node);
        return node;
    }
}
