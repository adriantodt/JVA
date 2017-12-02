package com.theorangehub.jva.utils;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NamedNodeMapIterator implements Iterator<Node> {
    private int i;
    private NamedNodeMap map;

    public NamedNodeMapIterator(NamedNodeMap map) {
        this.map = map;
        this.i = 0;
    }

    public boolean hasNext() {
        return map != null && i < map.getLength();
    }

    public Node next() {
        Node node = map.item(i);
        if (node == null) throw new NoSuchElementException();
        i++;
        return node;
    }
}
