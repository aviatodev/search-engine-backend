package com.aviato.searchengine.service;

import com.aviato.searchengine.util.Porter;

import java.util.List;

public interface StemmingService {
    Porter porter = new Porter();
    String stemAWord(String word);
    List<String> stemAListOfWords(List<String> listOfWords);
}
