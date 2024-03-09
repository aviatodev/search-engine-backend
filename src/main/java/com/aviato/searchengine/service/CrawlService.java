package com.aviato.searchengine.service;

import java.io.IOException;
import java.util.*;

public interface CrawlService {
    void initializeCrawlService(String url) throws IOException;
    void loadPage() throws IOException;

    List<String> extractWords() throws IOException;

    List<String> extractLinks() throws IOException;

    Map<String, String> extractMetadata() throws IOException;
}