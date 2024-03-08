package com.aviato.searchengine.service.impl;

import com.aviato.searchengine.service.PorterStemService;
import opennlp.tools.stemmer.PorterStemmer;

import java.util.ArrayList;
import java.util.List;

public class PorterStemServiceImpl implements PorterStemService {
    private static PorterStemmer porterStemmer = new PorterStemmer();

    private List<String> stemmedWordList;

    @Override
    public List<String> stemWordList(List<String> wordList) {
        this.stemmedWordList = new ArrayList<>();
        for (String word: wordList) {
            stemmedWordList.add(porterStemmer.stem(word));
        }
        return this.stemmedWordList;
    }
}
