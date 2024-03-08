package com.aviato.searchengine.service.impl;

import com.aviato.searchengine.service.CrawlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class CrawlServiceImpl implements CrawlService {
    private String url;

    @Override
    public void initializeUrl(String url) {
        this.url = url;
    }

    @Override
    public Document loadPage() throws IOException {
        return Jsoup.connect(this.url).get();
    }

    @Override
    public List<String> extractWords() throws IOException {
        List<String> wordList = new ArrayList<>();

        // Fetch and parse an HTML document from a URL
        Document document = this.loadPage();

        // Extract all text within any tag from the HTML document
        Elements elements = document.select("*");
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
        Document document = this.loadPage();

        // Extract all links in the HTML document
        Elements links = document.select("a");

        // Store all links to urlList and return
        for (Element link : links) {
            urlList.add(link.attr("href"));
        }
        return urlList;
    }

    @Override
    public Map<String, String> extractMetadata() throws IOException {
        Map<String, String> metadataMap = new HashMap<>();

        // Fetch and parse an HTML document from a URL
        Document doc = this.loadPage();

        // Extract metadata from the HTML document
        Elements metaElements = doc.select("meta");

        // Iterate over the meta elements and retrieve metadata
        for (Element meta : metaElements) {
            metadataMap.put(meta.attr("name"), meta.attr("content"));
        }
        return metadataMap;
    }
}
