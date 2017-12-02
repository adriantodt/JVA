package com.theorangehub.jva.model;

public interface AudioStats extends IJVA {
    long getBlocksDecoded();

    long getBuffersLost();

    long getBuffersPlayed();
}
