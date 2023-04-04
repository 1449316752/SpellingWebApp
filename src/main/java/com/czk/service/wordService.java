package com.czk.service;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordRecord;

import java.util.List;

public interface wordService {
    List<Word> getWordList();

    List<WordRecord> getWordRecords(User user);
}
