package com.theorangehub.jva.model;

import java.net.URI;

public interface Track extends IJVA {
    long getId();

    long getLength();

    String getName();

    URI getUri();

    boolean isCurrent();
}
