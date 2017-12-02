package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.CurrentTrack;
import com.theorangehub.jva.model.Playlist;
import com.theorangehub.jva.model.Track;
import com.theorangehub.jva.utils.XmlUtils;
import javafx.util.Pair;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static com.theorangehub.jva.utils.XmlUtils.attributeMap;
import static com.theorangehub.jva.utils.XmlUtils.stream;
import static org.apache.commons.text.StringEscapeUtils.unescapeXml;

public class CurrentTrackImpl implements CurrentTrack {
    private static Map<String, Map<String, String>> mapCategories(Element list) {
        return stream(list.getChildNodes())
            .filter(node -> node instanceof Element)
            .map(obj -> ((Element) obj))
            .filter(element -> element.getTagName().equals("information"))
            .flatMap(element -> stream(element.getChildNodes()))
            .filter(node -> node instanceof Element)
            .map(obj -> ((Element) obj))
            .filter(element -> element.getTagName().equals("category"))
            .map(element -> new Pair<>(attributeMap(element).get("name"), mapInfos(element)))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private static Map<String, String> mapInfos(Element list) {
        return stream(list.getChildNodes())
            .filter(node -> node instanceof Element)
            .map(obj -> ((Element) obj))
            .filter(element -> element.getTagName().equals("info"))
            .map(element -> new Pair<>(attributeMap(element).get("name"), ((Text) element.getFirstChild()).getWholeText()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private final String artist;
    private final String genre;
    private final long id;
    private final JVA jva;
    private final long length, time;
    private final double position;
    private final String title;
    private final URI uri, artworkUri;

    CurrentTrackImpl(JVA jva, Element element, Playlist playlist) {
        this.jva = jva;
        Map<String, String> status = XmlUtils.childMap(element);
        this.id = Long.parseLong(status.get("currentplid"));
        this.length = Long.parseLong(status.get("length"));
        this.time = Long.parseLong(status.get("time"));
        this.position = Double.parseDouble(status.get("position"));
        this.uri = playlist.getTrack(this.id).map(Track::getUri).orElse(null);

        Map<String, String> meta = mapCategories(element).getOrDefault("meta", Collections.emptyMap());

        this.title = unescapeXml(meta.get("title"));
        this.artist = unescapeXml(meta.get("artist"));
        this.genre = unescapeXml(meta.get("genre"));
        this.artworkUri = URI.create(unescapeXml(meta.get("artwork_url")));
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public URI getArtworkUri() {
        return artworkUri;
    }

    @Override
    public String getGenre() {
        return genre;
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
    public double getPosition() {
        return position;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format(
            "CurrentTrack{artist='%s', genre='%s', id=%d, length=%d, time=%d, position=%s, title='%s', uri=%s, artworkUri=%s}",
            artist, genre, id, length, time, position, title, uri, artworkUri
        );
    }
}
