package org.algorithm.leetcode.daily;

import java.lang.management.ManagementPermission;
import java.util.*;

public class FindTheCity {
    class Graph {
        Node[] nodes;
        int edgeSum = 0;
        public Graph(int size, int[][] edges) {
            this.nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(i);
            }
            build(edges);
        }

        private void build(int[][] edges) {
            for (int[] edge: edges) {
                int val = edge[0];
                int to = edge[1];
                int weight = edge[2];
                edgeSum += weight;
                Node fromNode = nodes[val];
                Node toNode = nodes[to];
                nodes[val] = fromNode;
                nodes[to] = toNode;
                Edge e = new Edge(fromNode, toNode, weight);
                fromNode.addEdge(e);
                Edge eReverse = new Edge(toNode, fromNode, weight);
                toNode.addEdge(eReverse);
            }
        }

        public Map<Integer, Integer> getMapCount(int distanceLimit) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Node node: this.nodes) {
                Map<Node, Integer> disMap = new HashMap<>();
                process(node, distanceLimit, 0, disMap);
                map.put(node.val, disMap.size() - 1);
            }
            return map;
        }

        private void process(Node node, int distanceLimit, int distance, Map<Node, Integer> disMap) {
            if (distance > distanceLimit) {
                return;
            }
            disMap.put(node, distance);
            for (Edge edge : node.edges) {
                if(canGo(disMap, edge.to, distance + edge.weight)) {
                    process(edge.to, distanceLimit, distance + edge.weight, disMap);
                }
            }
        }

        private boolean canGo(Map<Node, Integer> disMap, Node to, int distance) {
            return !disMap.containsKey(to) || disMap.get(to) > distance;
        }
    }
    class Node {
        int val;
        List<Edge> edges;

        public Node(int val) {
            this.val = val;
            edges = new ArrayList<>();
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }
    }
    class Edge {
        Node from;
        Node to;
        int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Graph g = new Graph(n, edges);
        Map<Integer, Integer> countMap = g.getMapCount(distanceThreshold);
        System.out.println(countMap);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(countMap.entrySet());
        list.sort((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o1.getValue() - o2.getValue();
            }
            return o2.getKey() - o1.getKey();
        });
        return list.get(0).getKey();
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][]{new int[]{0,1,10},new int[]{0,2,1},new int[]{2,3,1},new int[]{1,3,1},new int[]{1,4,1},new int[]{4,5,10}};
        int dis = 20;
        int val = new FindTheCity().findTheCity(n, edges, dis);
        System.out.println(val);
    }
}
