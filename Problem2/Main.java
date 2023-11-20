package net.medvediev;

public class Main {
    public static void main(String[] args) {
        Node root = createTree();
        StringBuilder sb = new StringBuilder();

        // Create an instance of Tree Serializer

        // TreeSerializer_Q1 serializer = new TreeSerializer_Q1();
        TreeSerializer_Q2 serializer = new TreeSerializer_Q2();

        
        try {
            // Serialize the tree
            String serializedTree = serializer.serialize(root);
            System.out.println("Serialized tree:   " + serializedTree);

            Node deserializedTree = serializer.deserialize(serializedTree);
            System.out.println("Deserialized tree: " + preOrderTraversal(deserializedTree, sb));

            if (areTreesEqual(root, deserializedTree)) {
                System.out.println("Serialization and deserialization successful. The trees are equal.");
            } else {
                System.out.println("Serialization and deserialization failed. The trees are not equal.");
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Node createTree() {
        Node root = new Node();
        root.num = 1;

        root.left = new Node();
        root.left.num = 2;
        root.right = new Node();
        root.right.num = 1;

        root.left.left = new Node();
        root.left.left.num = 7;
        root.left.right = new Node();
        root.left.right.num = 5;

        root.right.right = new Node();
        root.right.right.num = 28;

        root.left.left.left = new Node();
        root.left.left.left.num = 4;

        // Introduce a cycle by making the last node point to an ancestor
        // root.left.right.right = root;

        return root;
    }

    private static boolean areTreesEqual(Node tree1, Node tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }

        return (tree1.num == tree2.num) &&
                areTreesEqual(tree1.left, tree2.left) &&
                areTreesEqual(tree1.right, tree2.right);
    }

    private static String preOrderTraversal(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append('#').append(".");
            return null;
        }

        sb.append(node.num).append('.');
        preOrderTraversal(node.left, sb);
        preOrderTraversal(node.right, sb);

        return sb.toString();
    }
}
