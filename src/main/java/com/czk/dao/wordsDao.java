package com.czk.dao;

import com.czk.domain.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface wordsDao {
    @Select("select * from word_cet4_h")
    List<Word> getWordList();
}
