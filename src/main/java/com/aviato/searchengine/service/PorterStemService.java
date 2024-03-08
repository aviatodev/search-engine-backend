package com.aviato.searchengine.service;

import opennlp.tools.stemmer.PorterStemmer;

import java.util.List;

public interface PorterStemService {

    List<String> stemWordList(List<String> wordList);
}
