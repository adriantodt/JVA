package com.theorangehub.jva.model;

public interface VideoStats extends IJVA {
    long getBlocksDecoded();

    long getFramesDisplayed();

    long getFramesLost();
}
