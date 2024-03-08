package com.aviato.searchengine.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public interface CrawlService {
    void initializeUrl(String url);
    Document loadPage() throws IOException;

    List<String> extractWords() throws IOException;

    List<String> extractLinks() throws IOException;

    Map<String, String> extractMetadata() throws IOException;
}