package com.theorangehub.jva.utils;

import javafx.util.Pair;
import org.w3c.dom.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class XmlUtils {
    public static Map<String, String> attributeMap(Element element) {
        if (!element.hasAttributes()) {
            return Collections.emptyMap();
        }

        Map<String, String> map = new LinkedHashMap<>();
        for (Node node : iterable(element.getAttributes())) {
            Attr attr = (Attr) node;
            map.put(attr.getName(), attr.getValue());
        }

        return map;
    }

    public static Map<String, String> childMap(Element element) {
        return stream(element.getChildNodes())
            .filter(node -> node instanceof Element)
            .map(obj -> ((Element) obj))
            .filter(e -> e.getFirstChild() instanceof Text)
            .map(e -> new Pair<>(e.getTagName(), ((Text) e.getFirstChild()).getWholeText()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    public static Iterable<Node> iterable(NodeList nodeList) {
        return () -> new NodeListIterator(nodeList);
    }

    public static Iterable<Node> iterable(NamedNodeMap nodeMap) {
        return () -> new NamedNodeMapIterator(nodeMap);
    }

    public static Stream<Node> stream(NodeList nodeList) {
        return StreamSupport.stream(iterable(nodeList).spliterator(), false);
    }

    public static Stream<Node> stream(NamedNodeMap nodeMap) {
        return StreamSupport.stream(iterable(nodeMap).spliterator(), false);
    }

    public static Stream<Node> parallelStream(NodeList nodeList) {
        return StreamSupport.stream(iterable(nodeList).spliterator(), true);
    }

    public static Stream<Node> parallelStream(NamedNodeMap nodeMap) {
        return StreamSupport.stream(iterable(nodeMap).spliterator(), true);
    }
}
