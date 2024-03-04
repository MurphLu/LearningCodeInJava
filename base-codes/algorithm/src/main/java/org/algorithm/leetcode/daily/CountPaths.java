package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.sql.Timestamp;
import java.util.*;

// 一条路径上只允许有一个质数节点，求一共有多少条不同路径
public class CountPaths {
    static class Edge {
        Node from;
        Node to;

        public Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }

    static class Node{
        int val;
        List<Edge> edges;

        public Node(int val) {
            this.val = val;
            this.edges = new ArrayList<>();
        }

        public void addEdge(Edge edge) {
            this.edges.add(edge);
        }
    }

    static class Tree {
        Map<Integer, Node> map;

        public Tree(Map<Integer, Node> map) {
            this.map = map;
        }

        public Node getNode(int val) {
            return map.get(val);
        }
    }

    private Tree buildTree(int n, int[][] edges) {
        Map<Integer, Node> map = new HashMap<>();
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            Node na = map.getOrDefault(a, new Node(a));
            Node nb =  map.getOrDefault(b, new Node(b));

            Edge aTob = new Edge(na, nb);
            Edge bToA = new Edge(nb, na);

            na.addEdge(aTob);
            nb.addEdge(bToA);
            map.put(a, na);
            map.put(b , nb);
        }
        return new Tree(map);
    }

    private Set<Integer> getPrimeNode(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) set.add(i);
        }
        return set;
    }
    
    private boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i <= 100000 ; i++) {
            if (i == 2) continue;
            if (i == 1) {
                list.add(new int[]{1, 2});
            } else {
                list.add(new int[]{2, i});
            }

        }
//        Set<Integer> primeNode = new CountPaths().getPrimeNode(10000);
        long l = new CountPaths().countPaths2(100000, list.toArray(new int[][]{}));
        System.out.println(l);
    }

    public long countPaths(int n, int[][] edges) {
        Tree tree = buildTree(n, edges);
        Set<Integer> primeNode = getPrimeNode(n);
        Integer[] array = primeNode.toArray(new Integer[]{});
        Map<Integer, List<Long>> sizeMap = new HashMap<>();
        for (int val : array) {
            Node node = tree.getNode(val);
            List<Long> childNodeCounts = getLongs(val, node, primeNode);
            sizeMap.put(val, childNodeCounts);
        }
        return countResult(sizeMap);
    }



    private List<Long> getLongs(int val, Node node, Set<Integer> primeNode) {
        List<Long> childNodeCounts = new ArrayList<>();
        for(Edge edge : node.edges) {
            Node toNode = edge.to;
            if (primeNode.contains(toNode.val)) {
                continue;
            }
            Set<Integer> traveled = new HashSet<>();
            traveled.add(val);
            long childNodeCount = getChildNodeCount(toNode, primeNode, traveled);
            childNodeCounts.add(childNodeCount);
        }
        return childNodeCounts;
    }

    private long getChildNodeCount(Node node, Set<Integer> primeNode, Set<Integer> traveled) {
        if (primeNode.contains(node.val) || traveled.contains(node.val)) {
            return 0;
        }
        traveled.add(node.val);
        long childCount = 0;
        for(Edge edge : node.edges) {
            Node toNode = edge.to;
            childCount += getChildNodeCount(toNode, primeNode, traveled);
        }
        return childCount + 1;
    }


    /// use merge non-prime-node to solve this question
    static class TreeNode {
        int val;
        boolean isUnion;
        List<TreeNode> children;

        Set<Integer> nodeSet;

        public TreeNode(int val, boolean isUnion) {
            this.val = val;
            this.isUnion = isUnion;
            children = new ArrayList<>();
            if (isUnion) {
                nodeSet = new HashSet<>();
                nodeSet.add(val);
            }
        }

        public void merge(TreeNode node) {
            if (this.isUnion && node.isUnion) {
                nodeSet.addAll(node.nodeSet);
                children.addAll(node.children);
                for(TreeNode child : node.children) {
                    child.children.remove(node);
                    child.children.add(this);
                }
            }
        }

        public void addChild(TreeNode node) {
            children.add(node);
        }
    }
    private Long countResult(Map<Integer, List<Long>> sizeMap) {
        long sum = 0;
        for(Map.Entry<Integer, List<Long>> entry: sizeMap.entrySet()) {
            List<Long> value = entry.getValue();
            long count1 = 0;
            List<Long> moreThanOne = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                long val = value.get(i);
                sum += val;
                if (val == 1) {
                    count1 += 1;
                } if (val > 1) {
                    moreThanOne.add(val);
                }
            }
            if (count1 > 1) {
                //5, 4,3,2,1
                sum += (count1 - 1) * count1 / 2;
            }
            moreThanOne.add(count1);
            for (int i = 0; i < moreThanOne.size(); i++) {
                long v1 = moreThanOne.get(i);
                for (int j = i + 1; j < moreThanOne.size(); j++) {
                    long v2 = moreThanOne.get(j);
                    sum += v1 * v2;
                }
            }
        }
        return sum;
    }
    public long countPaths2(int n, int[][] edges) {
        System.out.println(new Date());
        Set<Integer> primeNodes = getPrimeNode(n);
        System.out.println(new Date());
        Map<Integer, TreeNode> map = buildTreeNode(n, edges, primeNodes);
        System.out.println(new Date());
        Map<Integer, List<Long>> childNodeSize = new HashMap<>();
        for(int nodeVal : primeNodes) {
            TreeNode treeNode = map.get(nodeVal);
            List<Long> sizeList = new ArrayList<>();
            for (TreeNode node : treeNode.children) {
                if(node.isUnion) sizeList.add((long) node.nodeSet.size());
            }
            childNodeSize.put(nodeVal, sizeList);
        }
        System.out.println(new Date());
        return countResult(childNodeSize);
    }

    private Map<Integer, TreeNode> buildTreeNode(int n, int[][] edges, Set<Integer> primeNodes) {

        Map<Integer, TreeNode> map = new HashMap<>();
        for(int[] edge : edges) {
            int p1 = edge[0];
            int p2 = edge[1];
            TreeNode tn1 = createNode(p1, map, primeNodes);
            TreeNode tn2 = createNode(p2, map, primeNodes);
            if (tn1.isUnion && tn2.isUnion) {
                TreeNode bigSet = tn1.nodeSet.size() > tn2.nodeSet.size() ? tn1 : tn2;
                TreeNode smallSet = bigSet == tn1 ? tn2 : tn1;
                bigSet.merge(smallSet);
                if (bigSet.nodeSet.size() == 2) {
                    smallSet = bigSet;
                }
                for(int i:smallSet.nodeSet) {
                    map.put(i, bigSet);
                }
            } else {
                tn1.addChild(tn2);
                tn2.addChild(tn1);
                map.put(p1, tn1);
                map.put(p2, tn2);
            }
        }
        return map;
    }

    private TreeNode createNode(int val, Map<Integer, TreeNode> map, Set<Integer> primeNode) {
        boolean isCount = !primeNode.contains(val);
        return map.getOrDefault(val, new TreeNode(val, isCount));
    }

}
