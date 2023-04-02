package com.czk.dao;

import com.czk.domain.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface wordsDao {
    List<Word> getWordList();
}
