package org.algorithm.graph;

import java.util.*;

public class Graph {
    Map<Integer, Node> nodes;
    Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public class Node {
        int val;
        int in;
        int out;
        List<Node> nexts;
        List<Edge> edges;

        public Node(int val) {
            this.val = val;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public class Edge {
        int weight;
        Node from;
        Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}
