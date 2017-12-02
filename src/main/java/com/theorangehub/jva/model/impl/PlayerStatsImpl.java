package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.*;
import javafx.util.Pair;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.Map;
import java.util.stream.Collectors;

import static com.theorangehub.jva.utils.XmlUtils.stream;

public class PlayerStatsImpl implements PlayerStats {

    private final AudioStats audioStats;
    private final InputStats inputStats;
    private final JVA jva;
    private final OutputStats outputStats;
    private final VideoStats videoStats;

    PlayerStatsImpl(JVA jva, Element statsType) {
        this.jva = jva;

        Map<String, String> stats = stream(statsType.getChildNodes())
            .filter(node -> node instanceof Element)
            .map(obj -> ((Element) obj))
            .filter(element -> element.getFirstChild() instanceof Text)
            .map(element -> new Pair<>(element.getTagName(), ((Text) element.getFirstChild()).getWholeText()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        this.audioStats = new AudioStatsImpl(
            jva,
            Long.parseLong(stats.get("decodedaudio")),
            Long.parseLong(stats.get("lostabuffers")),
            Long.parseLong(stats.get("playedabuffers"))
        );

        this.inputStats = new InputStatsImpl(
            jva,
            Long.parseLong(stats.get("averageinputbitrate")),
            Long.parseLong(stats.get("readbytes")),
            Long.parseLong(stats.get("averagedemuxbitrate")),
            Double.parseDouble(stats.get("demuxbitrate")),
            Long.parseLong(stats.get("demuxreadbytes")),
            Long.parseLong(stats.get("demuxcorrupted")),
            Long.parseLong(stats.get("demuxdiscontinuity")),
            Long.parseLong(stats.get("demuxreadpackets")),
            Double.parseDouble(stats.get("inputbitrate")),
            Long.parseLong(stats.get("readpackets"))
        );

        this.outputStats = new OutputStatsImpl(
            jva,
            Long.parseLong(stats.get("sentbytes")),
            Long.parseLong(stats.get("sentpackets")),
            Long.parseLong(stats.get("sendbitrate"))
        );

        this.videoStats = new VideoStatsImpl(
            jva,
            Long.parseLong(stats.get("decodedvideo")),
            Long.parseLong(stats.get("displayedpictures")),
            Long.parseLong(stats.get("lostpictures"))
        );
    }

    @Override
    public AudioStats getAudioStats() {
        return audioStats;
    }

    @Override
    public InputStats getInputStats() {
        return inputStats;
    }

    @Override
    public OutputStats getOutputStats() {
        return outputStats;
    }

    @Override
    public VideoStats getVideoStats() {
        return videoStats;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("PlayerStats{audioStats=%s, inputStats=%s, outputStats=%s, videoStats=%s}", audioStats, inputStats, outputStats, videoStats);
    }
}
