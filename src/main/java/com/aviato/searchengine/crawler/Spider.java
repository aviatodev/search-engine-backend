package com.aviato.searchengine.crawler;

import com.aviato.searchengine.service.impl.CrawlServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spider {
    private List<String> urlList;
    private List<String> visitedUrlList;
    private Map<String, List<String>> MapOfNestedUrlList;
    private Map<String, List<String>> MapOfWordList;
    private Map<String, Map<String, String>> MapOfMetaDataMap;

    private CrawlServiceImpl crawlServiceImpl;

    public Spider(String url) {
        this.urlList = new ArrayList<>();
        this.urlList.add(url);
        this.visitedUrlList = new ArrayList<>();
        this.MapOfNestedUrlList = new HashMap<>();
        this.MapOfWordList = new HashMap<>();
        this.MapOfMetaDataMap = new HashMap<>();
    }

    public Spider(List<String> urlList) {
        this.urlList = urlList;
        this.visitedUrlList = new ArrayList<>();
        this.MapOfNestedUrlList = new HashMap<>();
        this.MapOfWordList = new HashMap<>();
        this.MapOfMetaDataMap = new HashMap<>();
    }

    public void visitFirstPage() throws IOException {
        if (this.urlList != null && !this.urlList.isEmpty()) {
            String targetUrl = urlList.get(0);
            urlList.remove(0);
            visitedUrlList.add(targetUrl);

            this.crawlServiceImpl = new CrawlServiceImpl();
            this.crawlServiceImpl.initializeUrl(targetUrl);

            List<String> extractedListOfLinks = crawlServiceImpl.extractLinks();
            this.MapOfNestedUrlList.put(targetUrl, extractedListOfLinks);

            List<String> extractedListOfWords = crawlServiceImpl.extractWords();
            this.MapOfWordList.put(targetUrl, extractedListOfWords);
            Map<String, String> extractedListOfMetadata = crawlServiceImpl.extractMetadata();
            this.MapOfMetaDataMap.put(targetUrl, extractedListOfMetadata);

            String notVisitedUrl;
            for (String extractedListOfLink : extractedListOfLinks) {
                notVisitedUrl = extractedListOfLink;
                if (!this.visitedUrlList.contains(notVisitedUrl)) {
                    this.urlList.add(notVisitedUrl);
                }
            }
        }
    }

    public void visitNumberOfPage(int number, boolean isVisitAll) throws IOException {
        if (this.urlList == null || this.urlList.isEmpty() || number <= 0) {
            return;
        }
        if (isVisitAll) {
            while (!this.urlList.isEmpty()) {
                this.visitFirstPage();
            }
        } else {
            while (!this.urlList.isEmpty() && number > 0) {
                this.visitFirstPage();
                number -= 1;
            }
        }
    }

    public List<String> getMapOfWordList(String url) {
        return this.MapOfWordList.get(url);
    }

    public List<String> getMapOfNestedUrlList(String url) {
        return this.MapOfNestedUrlList.get(url);
    }

    public Map<String, String> getMapOfMetadata(String url) {
        return this.MapOfMetaDataMap.get(url);
    }
}
