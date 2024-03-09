package com.aviato.searchengine.entity;

import com.aviato.searchengine.entity.Spider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class SpiderTest {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    void SpiderCrawlFirstPage() throws IOException {
        String url = "https://www.cse.ust.hk/~kwtleung/COMP4321/testpage.htm";
        String backupUrl = "https://comp4321-hkust.github.io/testpages/testpage.htm";

        Spider spider = new Spider(url);
        logger.error("Spider created");
        spider.visitPage();

        Map<String, List<String>> MapOfNestedUrlList = spider.getMapOfNestedUrlList();
        logger.error("MapOfNestedUrlList has a size of {}", MapOfNestedUrlList.size());
        StringBuilder stringBuilderForNestedUrl = new StringBuilder();
        MapOfNestedUrlList.forEach((urlKey, nestedLinkListValue) -> {
            logger.error("Url: {}", urlKey);
            for (String word: nestedLinkListValue) {
                stringBuilderForNestedUrl.append(word).append(" ");
            }
            logger.error("NestedUrl: {}", stringBuilderForNestedUrl.toString().trim());
        });

        Map<String, List<String>> mapOfWordList = spider.getMapOfWordList();
        logger.error("mapOfWordList has a size of {}", mapOfWordList.size());
        StringBuilder stringBuilderForWordList = new StringBuilder();
        mapOfWordList.forEach((urlKey, wordListValue) -> {
            logger.error("Url: {}", urlKey);
            for (String word: wordListValue) {
                stringBuilderForWordList.append(word).append(" ");
            }
            logger.error("Words: {}", stringBuilderForWordList.toString().trim());
        });
    }

    @Test
    void SpiderCrawl3Pages() throws IOException {
        String url = "https://www.cse.ust.hk/~kwtleung/COMP4321/testpage.htm";
        // String backupUrl = "https://comp4321-hkust.github.io/testpages/testpage.htm";

        Spider spider = new Spider(url);
        logger.error("Spider created");
        spider.visitNumberOfPage(3, false);
        Map<String, List<String>> mapOfWordList = spider.getMapOfWordList();
        logger.error("mapOfWordList has a size of {}", mapOfWordList.size());
        StringBuilder stringBuilder = new StringBuilder();
        mapOfWordList.forEach((urlKey, wordListValue) -> {
            logger.error("Url: {}", urlKey);
            for (String word: wordListValue) {
                stringBuilder.append(word).append(" ");
            }
            logger.error("Words: {}", stringBuilder.toString().trim());
        });
    }

    @Test
    void visitFirstPage() {
    }

    @Test
    void visitNumberOfPage() {
    }

    @Test
    void getMapOfWordList() {
    }

    @Test
    void getMapOfNestedUrlList() {
    }

    @Test
    void getMapOfMetadata() {
    }
}