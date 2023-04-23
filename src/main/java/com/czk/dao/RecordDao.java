package com.czk.dao;

import com.czk.domain.WordRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordDao {
    /**
     * 查找用户的单词记录
     * @param u_id
     * @param w_id
     * @return
     */
    @Select("select w_id,level,isgrasp from wordrecord where w_id=#{w_id} and u_id=#{u_id}")
    WordRecord isExistRecord(String u_id, int w_id);

    /**
     * 更新用户的单词记录level值
     * @param u_id
     * @param w_id
     * @param level
     * @return
     */
    @Update("update wordrecord set level=#{level} where w_id=#{w_id} and u_id=#{u_id}")
    int setRecord(String u_id,int w_id,int level);

    /**
     * 增加一条用户的单词记录
     * @param u_id
     * @param w_id
     * @param level
     * @return
     */
    @Insert("insert into wordrecord(u_id,w_id,level) values(#{u_id},#{w_id},#{level})")
    int addRecord(String u_id,int w_id,int level);
}
