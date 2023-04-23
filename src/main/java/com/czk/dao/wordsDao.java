package com.czk.dao;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordListType;
import com.czk.domain.WordRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface wordsDao {
    /**
     * 根据list_id获取对应的单词本
     * @return
     */
    @Select("select w_id,word,tranCn from words where list_id = #{list_id}")
    List<Word> getWords(int list_id);

    @Select("SELECT w.w_id,w.word,w.tranCn,COALESCE(wr.`level`,0)as`level` " +
            "from words w LEFT JOIN wordrecord wr ON w.w_id = wr.w_id and wr.u_id = #{u_id} " +
            "where w.list_id = #{list_id}")
    List<Word> getWordsAndRecord(int u_id,int list_id);

    /**
     * 获取用户的整个单词本的单词记录
     * @param user
     * @return
     */
    @Select("select w_id,level,isgrasp from wordrecord where u_id = #{u_id}")
    List<WordRecord> getRecords(User user);

    @Select("select * from wordslist")
    List<WordListType> getWordList();
}
