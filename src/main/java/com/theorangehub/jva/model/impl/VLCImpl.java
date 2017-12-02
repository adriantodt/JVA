package com.theorangehub.jva.model.impl;

import com.theorangehub.jva.JVA;
import com.theorangehub.jva.model.CurrentTrack;
import com.theorangehub.jva.model.PlayerInfo;
import com.theorangehub.jva.model.Playlist;
import com.theorangehub.jva.model.VLC;
import org.w3c.dom.Element;

public class VLCImpl implements VLC {
    private final CurrentTrack currentTrack;
    private final JVA jva;
    private final PlayerInfo playerInfo;
    private final Playlist playlist;

    public VLCImpl(JVA jva, Element statusXml, Element playlistXml) {
        this.jva = jva;
        this.playlist = new PlaylistImpl(jva, playlistXml);
        this.currentTrack = new CurrentTrackImpl(jva, statusXml, playlist);
        this.playerInfo = new PlayerInfoImpl(jva, statusXml);
    }

    @Override
    public CurrentTrack getCurrentTrack() {
        return currentTrack;
    }

    @Override
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    @Override
    public Playlist getPlaylist() {
        return playlist;
    }

    @Override
    public JVA getJVA() {
        return jva;
    }

    @Override
    public String toString() {
        return String.format("VLC{playlist=%s, currentTrack=%s, playerInfo=%s}", playlist, currentTrack, playerInfo);
    }
}
