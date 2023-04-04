package com.czk.dao;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface wordsDao {
    @Select("select * from word_cet4_h")
    List<Word> getWordList();

    @Select("select w_id,level,isgrasp from record_cet4_h where u_id = #{u_id}")
    List<WordRecord> getRecords(User user);
}
