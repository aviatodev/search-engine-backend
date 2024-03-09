package com.aviato.searchengine.service;

import java.io.IOException;
import java.util.List;

public interface RemoveStopWordsService {
    void initRemoveStopWordsService() throws IOException;
    boolean isStopWord(String word);
    List<String> removeStopWordsFromList(List<String> listOfWords);
}
