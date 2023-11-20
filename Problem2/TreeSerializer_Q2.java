package net.medvediev;

import java.util.HashSet;
import java.util.Set;

/**
 * Binary Tree serializer that detects cycles
 * .serialize(Node) returns a string representation of the tree using pre-order DFS
 * .deserialize(String) returns a Node object from the string representation
 */
public class TreeSerializer_Q2 implements TreeSerializer {

    private Set<Node> visitedNodes;

    /**
     * Serializes a binary tree into a string using pre-order DFS
     * @param Node - root node of the tree
     * @return String - serialized tree representation 
     * @throws RuntimeException if a cycle is detected
     */
    public String serialize(Node root) {
        visitedNodes = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("#").append(".");
            return;
        }

        if (visitedNodes.contains(node)) {
            throw new RuntimeException("Cyclic tree detected!");
        }

        visitedNodes.add(node);
        sb.append(node.num).append(".");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
        visitedNodes.remove(node);
    }

    /**
     * Deserializes a string representation of a binary tree into a Node object
     * @param String - serialized tree representation
     * @return Node - root node of the tree
     */
    public Node deserialize(String data) {
        String[] vals = data.split("\\.");
        visitedNodes = new HashSet<>();
        int[] index = {0};
        return deserializeHelper(vals, index);

    }

    private Node deserializeHelper(String[] vals, int[] index) {
        if (vals[index[0]].equals("#")) {
            index[0]++;
            return null;
        }

        Node curr = new Node();
        curr.num = Integer.parseInt(vals[index[0]]);
        index[0]++;

        if (visitedNodes.contains(curr)) {
            throw new RuntimeException("Cyclic tree detected!");
        }

        visitedNodes.add(curr);
        curr.left = deserializeHelper(vals, index);
        curr.right = deserializeHelper(vals, index);
        visitedNodes.remove(curr);

        return curr;
    }
}
