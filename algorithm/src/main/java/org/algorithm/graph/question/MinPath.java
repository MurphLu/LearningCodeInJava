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

    class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    class NodeHeap {
        private final Node[] nodes;
        private final Map<Node, Integer> heapPos;
        private final Map<Node, Integer> distanceMap;
        private int size = 0;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapPos = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (!inserted(node)) {
                this.nodes[size] = node;
                this.heapPos.put(node,size);
                size ++;
                heapify(node);
            } else if (inHeap(node)){
                if(distanceMap.get(node) > distance) {
                    distanceMap.put(node, distance);
                    heapify(node);
                }
            }
        }

        private boolean inHeap(Node node) {
            return heapPos.containsKey(node) && heapPos.get(node) != -1;
        }

        private boolean inserted(Node node) {
            return heapPos.containsKey(node);
        }

        private void heapify(Node node) {
            int idx = this.heapPos.get(node);
            int fIdx = idx == 0 ? idx : (idx - 1) / 2;

            boolean up = getDistance(idx) < getDistance(fIdx);
            while(up && idx > 0) {
                fIdx = (idx - 1) / 2;

                if (getDistance(fIdx) < getDistance(idx)) {
                    break;
                }
                swap(idx, fIdx);
                idx = fIdx;
            }
            if (up) return;

            int lcIdx = idx * 2 + 1;
            while ((lcIdx < size)) {
                lcIdx = idx * 2 + 1;
                int rcInx = idx * 2 + 2;

                int minIdx = rcInx < size && getDistance(rcInx) < getDistance(lcIdx) ? rcInx : lcIdx;
                minIdx = getDistance(minIdx) < getDistance(idx) ? minIdx : idx;
                if (minIdx == idx) { break;}
                swap(minIdx, idx);
                idx = minIdx;
            }
        }

        private int getDistance(int index) {
            return distanceMap.get(nodes[index]);
        }

        public NodeRecord pop() {
            if (size == 0) {
                return null;
            }
            Node node = nodes[0];
            heapPos.put(node, -1);
            if (size > 1) {
                nodes[0] = nodes[size - 1];
                heapPos.put(nodes[0], 0);
                heapify(nodes[0]);
            }
            size--;
            return new NodeRecord(node, distanceMap.get(node));
        }

        private void swap(int index1, int index2) {
            heapPos.put(nodes[index1], index2);
            heapPos.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    public Map<Node, Integer> dijkstraImprove(Node head, int size){
        NodeHeap heap = new NodeHeap(size);
        heap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!heap.isEmpty()) {
            NodeRecord record = heap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge: cur.edges) {
                heap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
