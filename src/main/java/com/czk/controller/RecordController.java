package com.czk.controller;

import com.czk.domain.CountLog;
import com.czk.service.RecordService;
import com.czk.tools.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 获取用户今天的练习的单词数
     * @param u_id
     * @return
     */
    @GetMapping("/getNowdayStudyWordSum/{u_id}")
    public Result getNowdayStudyWordSum(@PathVariable int u_id){
        Integer sum = recordService.getUserNowdayStudySum(u_id);
        if (sum != null){
            return Result.Success(sum);
        }
        return Result.Error("无记录");
    }

    /**
     * 获取用户所有单词的练习次数和
     * @param u_id
     * @return
     */
    @GetMapping("/getUserRecordCountSum/{u_id}")
    public Result getUserRecordCountSum(@PathVariable int u_id){
        Integer countSum = recordService.getUserRecordCountSum(u_id);
        if (countSum != null){
            return Result.Success(countSum);
        }
        return Result.Error("无记录");
    }

    /**
     * 获取用户学过的单词数
     * @param u_id
     * @return
     */
    @GetMapping("/getLearnedCount/{u_id}")
    public Result getLearnedCount(@PathVariable int u_id){
        Integer StudyCount = recordService.getLearnedCount(u_id);
        if (StudyCount != null){
            return Result.Success(StudyCount);
        }
        return Result.Error("无记录");
    }

    /**
     * 获取用户的生词或熟词的单词数
     * @param u_id
     * @return
     */
    @GetMapping("/getLearnedCountByIsgrasp")
    public Result getLearnedCountByIsgrasp(@RequestParam int u_id,@RequestParam int type){
        Integer StudyCount = recordService.getLearnedCountByIsgrasp(u_id,type);
        if (StudyCount != null){
            return Result.Success(StudyCount);
        }
        return Result.Error("无记录");
    }

    /**
     * 获取用户全部的日志（每天的学习单词数、每天的练习次数）
     * @param u_id
     * @return
     */
    @GetMapping("/getCountLogs/{u_id}")
    public Result getCountLogs(@PathVariable int u_id){
        List<CountLog> countLogs = recordService.getCountLogs(u_id);
        if (countLogs != null){
            return Result.Success(countLogs);
        }
        return Result.Error("无日志记录");
    }
}

