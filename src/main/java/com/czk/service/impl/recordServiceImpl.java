package com.czk.service.impl;

import com.czk.dao.RecordDao;
import com.czk.domain.CountLog;
import com.czk.domain.User;
import com.czk.domain.WordRecord;
import com.czk.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class recordServiceImpl implements RecordService {
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

    @Override
    public Map<String,Integer> getLearnedAndGraspCount(int u_id) {
        Integer graspCount = recordDao.getGraspCount(u_id,1);
        Integer learnedCount = recordDao.getLearnedCount(u_id);
        System.out.println(learnedCount+"  "+graspCount);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("learnedCount",learnedCount);
        map.put("graspCount",graspCount);
        return map;
    }

    @Override
    public boolean setRecordIsgrasp(int u_id, int w_id, int type) {
        return recordDao.setRecordIsgrasp(u_id,w_id,type) == 1;
    }

    @Override
    public Integer getRecordCount(int u_id, int w_id) {
        return recordDao.getRecordCount(u_id,w_id);
    }

    @Override
    public boolean setRecordCountAdd(int u_id, int w_id, int count) {
        Date date = new Date(new java.util.Date().getTime());//获取今天
        boolean flag = recordDao.setRecordCountAdd(u_id,w_id,count);
        recordDao.setRecordUpdateTime(u_id,w_id,date);
        //对今日计数日志处理
        Integer flag1 = recordDao.setCountLogWordAndLearnCount(u_id,date); //更新今日日志的单词学习数、设置练习次数+1
        if (flag1 == 0){ //设置失败，没有记录，创建一条
            Integer flag2 = recordDao.addCountLog(u_id,date);
            if (flag2 != 0){
                recordDao.setCountLogWordAndLearnCount(u_id,date);
            }
        }
        return flag;
    }

    @Override
    public Integer getUserNowdayStudySum(int u_id) {
        Integer sum = recordDao.getUserNowdayStudySum(u_id,new Date(new java.util.Date().getTime()));
        return sum;
    }

    @Override
    public Integer getUserRecordCountSum(int u_id) {
        return recordDao.getUserRecordCountSum(u_id);
    }

    @Override
    public Integer getLearnedCount(int u_id) {
        return recordDao.getLearnedCount(u_id);
    }

    @Override
    public Integer getLearnedCountByIsgrasp(int u_id, int type) {
        return recordDao.getGraspCount(u_id,type);
    }

    @Override
    public List<CountLog> getCountLogs(int u_id) {
        return recordDao.getCountLogs(u_id);
    }
}
