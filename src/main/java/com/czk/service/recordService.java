package com.czk.service;

import com.czk.domain.User;

public interface recordService {

    boolean isExistRecord(User user,int w_id);

    boolean setRecord(User user,int w_id,int level);

    boolean addRecord(User user,int w_id,int level);
}
