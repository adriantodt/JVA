package com.theorangehub.jva;

import com.theorangehub.jva.model.VLC;
import com.theorangehub.jva.model.impl.VLCImpl;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JVA {
    private final OkHttpClient httpClient;
    private final String target;
    private DocumentBuilderFactory factory;

    public JVA(String password) {
        this("http://127.0.0.1:8080", password);
    }

    public JVA(String target, String password) {
        this.target = target;

        String credential = Credentials.basic("", password);
        this.httpClient = new OkHttpClient.Builder()
            .authenticator((route, response) -> {
                if (response.request().header("Authorization") != null) return null;

                return response.request().newBuilder()
                    .header("Authorization", credential)
                    .build();
            })
            .build();

        factory = DocumentBuilderFactory.newInstance();
    }

    public void asyncGet(Callback callback) {
        new Thread(task(callback), "JVA asyncGet " + callback.toString()).run();
    }

    public void get(Callback callback) {
        try {
            callback.onSuccess(get());
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    public VLC get() throws IOException {
        try {
            return new VLCImpl(this, getStatusXml().getDocumentElement(), getPlaylistXml().getDocumentElement());
        } catch (ParserConfigurationException | SAXException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public ResponseBody getPlaylistPage() throws IOException {
        return httpClient.newCall(
            new Builder()
                .url(target + "/requests/playlist.xml")
                .header("User-Agent", "JVA 1.0")
                .header("Content-Type", "text/plain")
                .get()
                .build()
        ).execute().body();
    }

    public Document getPlaylistXml() throws IOException, SAXException, ParserConfigurationException {
        Document document = factory.newDocumentBuilder().parse(getPlaylistPage().byteStream());
        document.normalizeDocument();
        return document;
    }

    public ResponseBody getStatusPage() throws IOException {
        return httpClient.newCall(
            new Builder()
                .url(target + "/requests/status.xml")
                .header("User-Agent", "JVA 1.0")
                .header("Content-Type", "text/plain")
                .get()
                .build()
        ).execute().body();
    }

    public Document getStatusXml() throws IOException, SAXException, ParserConfigurationException {
        Document document = factory.newDocumentBuilder().parse(getStatusPage().byteStream());
        document.normalizeDocument();
        return document;
    }

    public void scheduleTask(Callback callback, long period, TimeUnit unit) {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(task(callback), 0, period, unit);
    }

    private Runnable task(Callback callback) {
        return () -> get(callback);
    }
}
