package com.aviato.searchengine.service.impl;

import com.aviato.searchengine.service.RemoveStopWordsService;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveStopWordsServiceImpl implements RemoveStopWordsService {
    private static HashSet<String> stopWordSet;

    @Override
    public void initRemoveStopWordsService() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/stopwords.txt");
        Path path = resource.getFile().toPath();
        stopWordSet = new HashSet<>(Files.readAllLines(path, StandardCharsets.UTF_8));
    }
    
    @Override
    public boolean isStopWord(String word) {
        return stopWordSet.contains(word);
    }

    @Override
    public List<String> removeStopWordsFromList(List<String> listOfWords) {
        List<String> wordsWithoutStopwords = new ArrayList<>();
        for (String word : listOfWords) {
            if (!stopWordSet.contains(word)) {
                wordsWithoutStopwords.add(word);
            }
        }
        return wordsWithoutStopwords;
    }
}
