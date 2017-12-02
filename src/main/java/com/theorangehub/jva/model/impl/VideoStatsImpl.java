package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.VideoStats;

public class VideoStatsImpl implements VideoStats {
    private final long blocksDecoded;
    private final long framesDisplayed;
    private final long framesLost;
    private final JVA jva;

    VideoStatsImpl(JVA jva, long blocksDecoded, long framesDisplayed, long framesLost) {
        this.jva = jva;
        this.blocksDecoded = blocksDecoded;
        this.framesDisplayed = framesDisplayed;
        this.framesLost = framesLost;
    }

    @Override
    public long getBlocksDecoded() {
        return blocksDecoded;
    }

    @Override
    public long getFramesDisplayed() {
        return framesDisplayed;
    }

    @Override
    public long getFramesLost() {
        return framesLost;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("VideoStats{blocksDecoded=%d, framesDisplayed=%d, framesLost=%d}", blocksDecoded, framesDisplayed, framesLost);
    }
}
