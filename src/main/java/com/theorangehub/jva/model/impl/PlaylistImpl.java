package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.Playlist;
import com.theorangehub.jva.model.Track;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class PlaylistImpl implements Playlist {

    private static List<Element> scanLeafs(Node source) {
        List<Element> elements = new LinkedList<>();
        scanLeafs(source, elements);
        return elements;
    }

    private static void scanLeafs(Node source, List<Element> result) {
        if (!(source instanceof Element)) {
            return;
        }

        Element element = (Element) source;

        switch (element.getTagName()) {
            case "leaf": {
                result.add(element);
                break;
            }

            case "node": {
                NodeList childs = element.getChildNodes();

                for (int i = 0; i < childs.getLength(); i++) {
                    scanLeafs(childs.item(i), result);
                }

                break;
            }
        }
    }

    private final JVA jva;
    private final TLongObjectMap<Track> tracks;

    PlaylistImpl(JVA jva, Element element) {
        this.jva = jva;
        this.tracks = new TLongObjectHashMap<>();

        scanLeafs(element)
            .stream()
            .map(e -> new TrackImpl(jva, e))
            .sorted(Comparator.comparingLong(TrackImpl::getId))
            .forEach(track -> tracks.put(track.getId(), track));
    }

    @Override
    public Optional<Track> getCurrentTrack() {
        return tracks.valueCollection().stream().filter(Track::isCurrent).findFirst();
    }

    @Override
    public Optional<Track> getTrack(long id) {
        return Optional.ofNullable(tracks.get(id));
    }

    @Override
    public List<Track> getTracks() {
        return Collections.unmodifiableList(new ArrayList<>(tracks.valueCollection()));
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("Playlist{tracks=%s}", tracks);
    }
}
