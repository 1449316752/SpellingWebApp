package com.czk.service;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordListType;
import com.czk.domain.WordRecord;

import java.util.List;

public interface WordService {
    List<Word> getWords(int list_id);

    List<Word> getWordsAndRecord(int u_id,int list_id);

    List<WordListType> getWordList();

    List<WordRecord> getWordRecords(User user);

    List<Word> getForgetWords(int u_id,int list_id,int type);
}
