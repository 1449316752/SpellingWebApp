package com.czk.service;

import com.czk.domain.CountLog;
import com.czk.domain.User;

import java.util.List;
import java.util.Map;

public interface RecordService {

    boolean isExistRecord(User user,int w_id);

    boolean setRecord(User user,int w_id,int level);

    boolean addRecord(User user,int w_id,int level);

    Map getLearnedAndGraspCount(int u_id);

    boolean setRecordIsgrasp(int u_id, int w_id, int type);

    Integer getRecordCount(int u_id, int w_id);

    boolean setRecordCountAdd(int parseInt, int w_id, int i);

    Integer getUserNowdayStudySum(int u_id);

    Integer getUserRecordCountSum(int u_id);

    Integer getLearnedCount(int u_id);

    Integer getLearnedCountByIsgrasp(int u_id, int type);

    List<CountLog> getCountLogs(int u_id);
}
