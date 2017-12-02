package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.PlayerInfo;
import com.theorangehub.jva.model.PlayerState;
import com.theorangehub.jva.model.PlayerStats;
import org.w3c.dom.Element;

import java.util.Map;

import static com.theorangehub.jva.utils.XmlUtils.childMap;
import static com.theorangehub.jva.utils.XmlUtils.stream;
import static org.apache.commons.text.StringEscapeUtils.unescapeXml;

public class PlayerInfoImpl implements PlayerInfo {
    private final long apiVersion;
    private final boolean fullscreen, repeating, looping, shuffled;
    private final JVA jva;
    private final PlayerStats playerStats;
    private final PlayerState state;
    private final String version;
    private final long volume;

    PlayerInfoImpl(JVA jva, Element root) {
        this.jva = jva;
        Map<String, String> map = childMap(root);

        this.playerStats = stream(root.getChildNodes())
            .filter(node -> node instanceof Element)
            .map(obj -> ((Element) obj))
            .filter(element -> element.getTagName().equals("stats"))
            .findFirst()
            .map(statsType -> new PlayerStatsImpl(jva, statsType))
            .orElse(null);

        this.fullscreen = Long.parseLong(map.get("fullscreen")) != 0;
        this.repeating = Boolean.parseBoolean(map.get("repeat"));
        this.shuffled = Boolean.parseBoolean(map.get("random"));
        this.looping = Boolean.parseBoolean(map.get("loop"));
        this.version = unescapeXml(map.get("version"));
        this.state = PlayerState.valueOf(map.get("state").toUpperCase());
        this.volume = Long.parseLong(map.get("volume"));
        this.apiVersion = Long.parseLong(map.get("apiversion"));
    }

    @Override
    public long getApiVersion() {
        return apiVersion;
    }

    @Override
    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    @Override
    public PlayerState getState() {
        return state;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public long getVolume() {
        return volume;
    }

    @Override
    public boolean isFullscreen() {
        return fullscreen;
    }

    @Override
    public boolean isLooping() {
        return looping;
    }

    @Override
    public boolean isRepeating() {
        return repeating;
    }

    @Override
    public boolean isShuffled() {
        return shuffled;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format(
            "PlayerInfo{apiVersion=%d, fullscreen=%s, repeating=%s, looping=%s, shuffled=%s, playerStats=%s, state=%s, version='%s', volume=%d}",
            apiVersion, fullscreen, repeating, looping, shuffled, playerStats, state, version, volume
        );
    }
}
