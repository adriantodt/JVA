package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.AudioStats;

public class AudioStatsImpl implements AudioStats {
    private final long blocksDecoded;
    private final long buffersLost;
    private final long buffersPlayed;
    private final JVA jva;

    AudioStatsImpl(JVA jva, long blocksDecoded, long buffersLost, long buffersPlayed) {
        this.jva = jva;
        this.blocksDecoded = blocksDecoded;
        this.buffersLost = buffersLost;
        this.buffersPlayed = buffersPlayed;
    }

    @Override
    public long getBlocksDecoded() {
        return blocksDecoded;
    }

    @Override
    public long getBuffersLost() {
        return buffersLost;
    }

    @Override
    public long getBuffersPlayed() {
        return buffersPlayed;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("AudioStats{blocksDecoded=%d, buffersLost=%d, buffersPlayed=%d}", blocksDecoded, buffersLost, buffersPlayed);
    }
}
