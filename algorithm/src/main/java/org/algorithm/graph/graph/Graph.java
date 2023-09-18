package org.algorithm.graph.graph;

import java.util.*;

public class Graph {
    public Map<Integer, Node> nodes;
    public Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public static Graph create(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] integers : matrix) {
            Integer from = integers[0];
            Integer to = integers[1];
            Integer weight = integers[3];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            Node nodeFrom = graph.nodes.get(from);
            Node nodeTo = graph.nodes.get(to);
            Edge edge = new Edge(weight, nodeFrom, nodeTo);

            nodeFrom.nexts.add(nodeTo);

            nodeFrom.out += 1;
            nodeTo.in += 1;

            nodeFrom.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}

