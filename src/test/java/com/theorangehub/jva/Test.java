package com.theorangehub.jva;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        JVA jva = new JVA("vsf");

        jva.scheduleTask(vlc -> System.out.println(vlc.getCurrentTrack().getTime()), 1, TimeUnit.SECONDS);
    }
}
