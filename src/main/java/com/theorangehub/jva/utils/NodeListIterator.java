package com.theorangehub.jva.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeListIterator implements Iterator<Node> {
    private int i;
    private NodeList list;

    public NodeListIterator(NodeList list) {
        this.list = list;
        this.i = 0;
    }

    public boolean hasNext() {
        return list != null && i < list.getLength();
    }

    public Node next() {
        Node node = list.item(i);
        if (node == null) throw new NoSuchElementException();
        i++;
        return node;
    }
}
