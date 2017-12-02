package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.InputStats;

public class InputStatsImpl implements InputStats {
    private final long averageInputBitrate;
    private final long bytesRead;
    private final long demuxAverageBitrate;
    private final double demuxBitrate;
    private final long demuxBytesRead;
    private final long demuxCorrupted;
    private final long demuxDiscontinuity;
    private final long demuxpacketsRead;
    private final double inputBitrate;
    private final JVA jva;
    private final long packetsRead;

    InputStatsImpl(JVA jva, long averageInputBitrate, long bytesRead, long demuxAverageBitrate, double demuxBitrate, long demuxBytesRead, long demuxCorrupted, long demuxDiscontinuity, long demuxpacketsRead, double inputBitrate, long packetsRead) {
        this.jva = jva;
        this.averageInputBitrate = averageInputBitrate;
        this.bytesRead = bytesRead;
        this.demuxAverageBitrate = demuxAverageBitrate;
        this.demuxBitrate = demuxBitrate;
        this.demuxBytesRead = demuxBytesRead;
        this.demuxCorrupted = demuxCorrupted;
        this.demuxDiscontinuity = demuxDiscontinuity;
        this.demuxpacketsRead = demuxpacketsRead;
        this.inputBitrate = inputBitrate;
        this.packetsRead = packetsRead;
    }

    @Override
    public long getAverageInputBitrate() {
        return averageInputBitrate;
    }

    @Override
    public long getBytesRead() {
        return bytesRead;
    }

    @Override
    public long getDemuxAverageBitrate() {
        return demuxAverageBitrate;
    }

    @Override
    public double getDemuxBitrate() {
        return demuxBitrate;
    }

    @Override
    public long getDemuxBytesRead() {
        return demuxBytesRead;
    }

    @Override
    public long getDemuxCorrupted() {
        return demuxCorrupted;
    }

    @Override
    public long getDemuxDiscontinuity() {
        return demuxDiscontinuity;
    }

    @Override
    public long getDemuxpacketsRead() {
        return demuxpacketsRead;
    }

    @Override
    public double getInputBitrate() {
        return inputBitrate;
    }

    @Override
    public long getPacketsRead() {
        return packetsRead;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format(
            "InputStats{averageInputBitrate=%d, bytesRead=%d, demuxAverageBitrate=%d, demuxBitrate=%s, demuxBytesRead=%d, demuxCorrupted=%d, " +
                "demuxDiscontinuity=%d, demuxpacketsRead=%d, inputBitrate=%s, packetsRead=%d}",
            averageInputBitrate, bytesRead, demuxAverageBitrate, demuxBitrate, demuxBytesRead, demuxCorrupted,
            demuxDiscontinuity, demuxpacketsRead, inputBitrate, packetsRead
        );
    }
}
