package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.OutputStats;

public class OutputStatsImpl implements OutputStats {
    private final long bytesSent;
    private final JVA jva;
    private final long packetsSent;
    private final long sendingBitrate;

    OutputStatsImpl(JVA jva, long bytesSent, long packetsSent, long sendingBitrate) {
        this.jva = jva;
        this.bytesSent = bytesSent;
        this.packetsSent = packetsSent;
        this.sendingBitrate = sendingBitrate;
    }

    @Override
    public long getBytesSent() {
        return bytesSent;
    }

    @Override
    public long getPacketsSent() {
        return packetsSent;
    }

    @Override
    public long getSendingBitrate() {
        return sendingBitrate;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("OutputStats{bytesSent=%d, packetsSent=%d, sendingBitrate=%d}", bytesSent, packetsSent, sendingBitrate);
    }
}
