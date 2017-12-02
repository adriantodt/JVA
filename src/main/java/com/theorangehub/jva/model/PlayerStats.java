package com.theorangehub.jva.model;

public interface PlayerStats extends IJVA {
    AudioStats getAudioStats();

    InputStats getInputStats();

    OutputStats getOutputStats();

    VideoStats getVideoStats();
}
