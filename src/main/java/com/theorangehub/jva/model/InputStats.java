package com.theorangehub.jva.model;

public interface InputStats extends IJVA {
    long getAverageInputBitrate();

    long getBytesRead();

    long getDemuxAverageBitrate();

    double getDemuxBitrate();

    long getDemuxBytesRead();

    long getDemuxCorrupted();

    long getDemuxDiscontinuity();

    long getDemuxpacketsRead();

    double getInputBitrate();

    long getPacketsRead();
}
