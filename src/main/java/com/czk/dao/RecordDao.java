package com.czk.dao;

import com.czk.domain.WordRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordDao {

    @Select("select w_id,level,isgrasp from record_cet4_h where w_id=#{w_id} and u_id=#{u_id}")
    WordRecord isExistRecord(String u_id, int w_id);

    @Update("update record_cet4_h set level=#{level} where w_id=#{w_id} and u_id=#{u_id}")
    int setRecord(String u_id,int w_id,int level);

    @Insert("insert into record_cet4_h(u_id,w_id,level) values(#{u_id},#{w_id},#{level})")
    int addRecord(String u_id,int w_id,int level);
}
