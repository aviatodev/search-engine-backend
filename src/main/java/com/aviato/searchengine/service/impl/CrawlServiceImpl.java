package com.aviato.searchengine.service.impl;

import com.aviato.searchengine.service.CrawlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CrawlServiceImpl implements CrawlService {
    private String url;
    private Document document;
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void initializeCrawlService(String url) throws IOException {
        this.url = url;
        this.loadPage();
    }

    @Override
    public void loadPage() throws IOException {
        this.document= Jsoup.connect(this.url).get();
    }

    @Override
    public List<String> extractWords() throws IOException {
        List<String> wordList = new ArrayList<>();

        // Fetch and parse an HTML document from a URL
        Document document = this.document;

        // Extract all text within any tag from the HTML document
        Elements elements = document.select("p");
        for (Element element : elements) {
            String text = element.text();
            if (!text.isEmpty()) {
                // Separate each word from a sentence by indicating whitespace
                String[] words = text.split("\\s+");
                wordList.addAll(Arrays.asList(words));
            }
        }
        return wordList;
    }

    @Override
    public List<String> extractLinks() throws IOException {
        List<String> urlList = new ArrayList<>();

        // Fetch and parse an HTML document from a URL
        Document document = this.document;

        // Extract all links in the HTML document
        Elements links = document.select("a");

        // Store all links to urlList and return
        for (Element link : links) {
            urlList.add(link.attr("abs:href"));
        }
        return urlList;
    }

    @Override
    public Map<String, String> extractMetadata() throws IOException {
        Map<String, String> metadataMap = new HashMap<>();

        // Fetch and parse an HTML document from a URL
        Document document = this.document;

        // Extract metadata from the HTML document
        Elements metaElements = document.select("meta");

        // Iterate over the meta elements and retrieve metadata
        for (Element meta : metaElements) {
            metadataMap.put(meta.attr("name"), meta.attr("content"));
        }
        return metadataMap;
    }
}
