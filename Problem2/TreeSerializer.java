package net.medvediev;

public interface TreeSerializer {
    String serialize(Node root);

    Node deserialize(String str);
}
