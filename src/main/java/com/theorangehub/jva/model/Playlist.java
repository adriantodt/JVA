package com.theorangehub.jva.model;

import java.util.List;
import java.util.Optional;

public interface Playlist extends IJVA {
    Optional<Track> getCurrentTrack();

    Optional<Track> getTrack(long id);

    List<Track> getTracks();
}
