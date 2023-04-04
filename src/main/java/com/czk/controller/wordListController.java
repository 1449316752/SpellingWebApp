package com.czk.controller;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordRecord;
import com.czk.service.recordService;
import com.czk.service.wordService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word")
public class wordListController {

    @Autowired
    private wordService wordService;

    @Autowired
    private recordService recordService;

    @GetMapping
    public Result getWordList(@RequestParam String u_id){
        System.out.println("controller");
        List<Word> wordList = wordService.getWordList();
        List<WordRecord> wordRecordList = wordService.getWordRecords(new User(u_id));
        for (WordRecord wr: wordRecordList) {
            int w_id = wr.getW_id();
            Word word = wordList.get(w_id-1);
            word.setLevel(wr.getLevel());
            word.setIgnore(wr.isGrasp());
            wordList.set(w_id-1,word);
        }
        return new Result(0,wordList,"ok");
    }

    @PutMapping
    public Result setWordRecord(@RequestBody User user,@RequestParam String w_id,@RequestParam String level){
        boolean is = recordService.isExistRecord(user, Integer.parseInt(w_id));
        System.out.println(w_id+" "+level);
        if (is){
            boolean b = recordService.setRecord(user, Integer.parseInt(w_id), Integer.parseInt(level));
            return new Result(b? Code.UPDATE_OK:Code.UPDATE_ERR,null,"这次修改了level");
        }else {
            boolean b = recordService.addRecord(user, Integer.parseInt(w_id), Integer.parseInt(level));
            return new Result(b? Code.UPDATE_OK:Code.UPDATE_ERR,null,"这次新增了记录");
        }
    }
}
