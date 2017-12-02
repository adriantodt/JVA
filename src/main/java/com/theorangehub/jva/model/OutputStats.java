package com.theorangehub.jva.model;

public interface OutputStats extends IJVA {
    long getBytesSent();

    long getPacketsSent();

    long getSendingBitrate();
}
