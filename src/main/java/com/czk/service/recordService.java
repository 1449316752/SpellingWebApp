package com.czk.service;

import com.czk.domain.User;

import java.util.List;
import java.util.Map;

public interface recordService {

    boolean isExistRecord(User user,int w_id);

    boolean setRecord(User user,int w_id,int level);

    boolean addRecord(User user,int w_id,int level);

    Map getLearnedAndGraspCount(int u_id);
}
