package org.algorithm.graph.question;

import org.algorithm.graph.graph.Edge;
import org.algorithm.graph.graph.Graph;
import org.algorithm.graph.UnionFind;
import org.algorithm.graph.graph.Node;

import java.util.*;

public class MinTree {
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1,null, null));
        edges.add(new Edge(3,null, null));
        edges.add(new Edge(4,null, null));
        edges.add(new Edge(2,null, null));
        edges.sort(Comparator.comparingInt((Edge e) -> e.weight));
        System.out.println(edges);
    }

    // kruskal 算法 无向图
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind<Node> uf = new UnionFind<>(new ArrayList<>(graph.nodes.values()));
        List<Edge> edges = new ArrayList<>(graph.edges);
        edges.sort(Comparator.comparingInt((Edge a) -> a.weight));

        Set<Edge> result = new HashSet<>();
        for (Edge e : edges) {
            if (uf.find(e.from) != uf.find(e.to)) {
                result.add(e);
                uf.union(e.from, e.to);
            }
        }
        return result;
    }

    // prim 无向图
    public static Set<Edge> primMST(Graph graph) {
        Set<Edge> result = new HashSet<>();
        Set<Node> nodes = new HashSet<>();
        PriorityQueue<Edge> p = new PriorityQueue<>();
        for (Node node : graph.nodes.values()) {
            if (!nodes.contains(node)) {
                nodes.add(node);
                p.addAll(node.edges);
                while (!p.isEmpty()) {
                    Edge e = p.poll();
                    if (!nodes.contains(e.to)) {
                        nodes.add(e.to);
                        result.add(e);
                        p.addAll(e.to.edges);
                    }
                }
            }
        }
        return result;
    }
}
