package com.theorangehub.jva.model;

public interface PlayerInfo extends IJVA {
    long getApiVersion();

    PlayerStats getPlayerStats();

    PlayerState getState();

    String getVersion();

    long getVolume();

    boolean isFullscreen();

    boolean isLooping();

    boolean isRepeating();

    boolean isShuffled();
}
