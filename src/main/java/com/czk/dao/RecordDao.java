package com.czk.dao;

import com.czk.domain.CountLog;
import com.czk.domain.WordRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

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

    /**
     * 查询用户已学的（有记录的）单词数
     * @param u_id
     * @return
     */
    @Select("SELECT COUNT(u_id) FROM wordrecord where u_id = #{u_id}")
    Integer getLearnedCount(int u_id);

    /**
     * 查询用户已掌握的（有记录且掌握的）单词数
     * @param u_id
     * @return
     */
    @Select("SELECT COUNT(isgrasp) as `graspCount`  FROM wordrecord WHERE u_id = #{u_id} and isgrasp = #{type}")
    Integer getGraspCount(int u_id,int type);

    @Update("update wordrecord set isgrasp=#{type} where w_id=#{w_id} and u_id=#{u_id}")
    int setRecordIsgrasp(int u_id, int w_id, int type);

    @Select("SELECT count from wordrecord where u_id = #{u_id} and w_id = #{w_id}")
    Integer getRecordCount(int u_id, int w_id);

    @Update("UPDATE wordrecord set count = #{count} where u_id = #{u_id} and w_id = #{w_id}")
    boolean setRecordCountAdd(int u_id, int w_id, int count);

    @Update("UPDATE wordrecord set updateTime = #{date} where w_id = #{w_id} and u_id = #{u_id}")
    boolean setRecordUpdateTime(int u_id, int w_id, Date date);

    @Select("SELECT count(w_id) from wordrecord where u_id = #{u_id} and updateTime = #{date}")
    Integer getUserNowdayStudySum(int u_id, Date date);

    @Select("Select sum(count) from wordrecord where u_id = #{u_id}")
    Integer getUserRecordCountSum(int u_id);

    @Update("UPDATE countlog set wordcount = (\n" +
            "\tSELECT COUNT(updateTime) FROM wordrecord where u_id = #{u_id} and updateTime = #{date}\n" +
            "),\n" +
            "learncount = learncount+1\n" +
            "where u_id = #{u_id} and date = #{date}")
    Integer setCountLogWordAndLearnCount(int u_id, Date date);

    @Insert("insert into countlog(u_id, date) VALUES (#{u_id},#{date})")
    Integer addCountLog(int u_id, Date date);

    @Select("select * from countlog where u_id = #{u_id}")
    List<CountLog> getCountLogs(int u_id);
}
