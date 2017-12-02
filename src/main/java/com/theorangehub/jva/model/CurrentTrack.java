package com.theorangehub.jva.model;

import java.net.URI;

public interface CurrentTrack extends IJVA {
    String getArtist();

    URI getArtworkUri();

    String getGenre();

    long getId();

    long getLength();

    double getPosition();

    long getTime();

    String getTitle();

    URI getUri();
}
