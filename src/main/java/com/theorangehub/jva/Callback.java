package com.theorangehub.jva;

import com.theorangehub.jva.model.VLC;

public interface Callback {
    void onSuccess(VLC vlc);

    default void onError(Exception exception) {
    }
}
