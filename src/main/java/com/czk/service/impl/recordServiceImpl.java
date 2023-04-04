package com.czk.service.impl;

import com.czk.dao.RecordDao;
import com.czk.domain.User;
import com.czk.domain.WordRecord;
import com.czk.service.recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class recordServiceImpl implements recordService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public boolean isExistRecord(User user,int w_id) {
        WordRecord is = recordDao.isExistRecord(user.getU_id(),w_id);
        return is != null;
    }

    @Override
    public boolean setRecord(User user, int w_id, int level) {
        int i = recordDao.setRecord(user.getU_id(), w_id, level);
        return i == 1;
    }

    @Override
    public boolean addRecord(User user, int w_id, int level) {
        int i = recordDao.addRecord(user.getU_id(), w_id, level);
        return i == 1;
    }
}
