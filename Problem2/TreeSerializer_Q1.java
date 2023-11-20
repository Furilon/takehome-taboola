package net.medvediev;
/**
 * Binary Tree serializer
 * .serialize(Node) returns a string representation of the tree using pre-order DFS
 * .deserialize(String) returns a Node object from the string representation
 */
public class TreeSerializer_Q1 implements TreeSerializer {

    /**
     * Serializes a binary tree into a string using pre-order DFS
     * @param Node - root node of the tree
     * @return String - serialized tree representation 
     */
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(Node rt, StringBuilder sb) {
        if (rt == null) {
            sb.append("#").append(".");
            return;
        }

        sb.append(rt.num).append(".");
        serializeHelper(rt.left, sb);
        serializeHelper(rt.right, sb);
    }

    /**
     * Deserializes a string representation of a binary tree into a Node object
     * @param String - serialized tree representation
     * @return Node - root node of the tree
     */
    public Node deserialize(String data) {
        String[] vals = data.split("\\.");
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

        curr.left = deserializeHelper(vals, index);
        curr.right = deserializeHelper(vals, index);

        return curr;
    }
}