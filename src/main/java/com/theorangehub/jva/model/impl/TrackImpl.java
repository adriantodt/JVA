package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.Track;
import com.theorangehub.jva.utils.XmlUtils;
import org.w3c.dom.Element;

import java.net.URI;
import java.util.Map;

public class TrackImpl implements Track {
    private final boolean current;
    private final long id;
    private final JVA jva;
    private final long length;
    private final String name;
    private final URI uri;

    TrackImpl(JVA jva, Element element) {
        this.jva = jva;
        Map<String, String> leaf = XmlUtils.attributeMap(element);
        this.id = Long.parseLong(leaf.get("id"));
        this.name = (leaf.get("name"));
        this.length = Long.parseLong(leaf.get("duration"));
        this.uri = URI.create((leaf.get("uri")));
        this.current = leaf.getOrDefault("current", "").equals("current");
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getLength() {
        return length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public boolean isCurrent() {
        return current;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("Track{current=%s, id=%d, length=%d, name='%s', uri=%s}", current, id, length, name, uri);
    }
}
