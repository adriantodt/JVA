package com.theorangehub.jva.model;

public interface VLC extends IJVA {
    CurrentTrack getCurrentTrack();

    PlayerInfo getPlayerInfo();

    Playlist getPlaylist();
}
