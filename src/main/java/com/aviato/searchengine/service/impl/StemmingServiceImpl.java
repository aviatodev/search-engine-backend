package com.aviato.searchengine.service.impl;

import com.aviato.searchengine.service.StemmingService;

import java.util.ArrayList;
import java.util.List;

public class StemmingServiceImpl implements StemmingService {
    @Override
    public String stemAWord(String word) {
        return porter.stripAffixes(word);
    }

    @Override
    public List<String> stemAListOfWords(List<String> listOfWords) {
        List<String> stemmedList = new ArrayList<>(listOfWords.size());
        for (String str : listOfWords) {
            String stemmed = porter.stripAffixes(str);
            stemmedList.add(stemmed);
        }
        return stemmedList;
    }
}
