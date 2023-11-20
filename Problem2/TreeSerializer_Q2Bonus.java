package net.medvediev;

import java.util.HashSet;
import java.util.Set;

public class TreeSerializer_Q2Bonus implements TreeSerializer {

    private Set<Node> visitedNodes = new HashSet<>();
    private Set<Node> serializedNodes = new HashSet<>();

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        visitedNodes.clear();
        serializedNodes.clear();
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
            if (!serializedNodes.contains(node)) {
                throw new RuntimeException("Cyclic tree detected!");
            }
            sb.append("#").append(".");
            return; // This node has already been serialized
        }

        visitedNodes.add(node);
        serializedNodes.add(node);

        sb.append(node.num).append(".");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);

        visitedNodes.remove(node);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] vals = data.split("\\.");
        visitedNodes.clear();
        int[] index = {0};
        return deserializeHelper(vals, index);

    }

    private Node deserializeHelper(String[] vals, int[] index) {
        if (index[0] >= vals.length || vals[index[0]].equals("#")) {
            index[0]++;
            return null;
        }

        Node curr = new Node();
        curr.num = Integer.parseInt(vals[index[0]]);
        index[0]++;

        if (visitedNodes.contains(curr)) {
            return null;  // Return the existing node if it has already been visited
        }

        visitedNodes.add(curr);
        curr.left = deserializeHelper(vals, index);
        curr.right = deserializeHelper(vals, index);
        visitedNodes.remove(curr);

        return curr;
    }
}
