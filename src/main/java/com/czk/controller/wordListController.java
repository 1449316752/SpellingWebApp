package com.czk.controller;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordListType;
import com.czk.domain.WordRecord;
import com.czk.service.recordService;
import com.czk.service.wordService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/word")
public class wordListController {

    @Autowired
    private wordService wordService;

    @Autowired
    private recordService recordService;

    /**
     * 获取单词本，并对有记录的单词添加记录
     * @param u_id
     * @return
     */
    @GetMapping
    public Result getWords(@RequestParam int u_id,@RequestParam int list_id){
        System.out.println("controller");
        List<Word> words = wordService.getWordsAndRecord(u_id,list_id);
        return new Result(Code.GET_OK,words,"获取单词本"+list_id+"成功");
    }

    @GetMapping("/wordlist")
    public Result getWordList(){
        List<WordListType> wordList = wordService.getWordList();
        return new Result(Code.GET_OK,wordList,"获取单词表成功");
    }

    /**
     * 对用户的单词level做修改，如果没有记录则创建一条记录
     * @param user
     * @param w_id
     * @param level
     * @return
     */
    @PutMapping
    public Result setWordRecord(@RequestBody User user,@RequestParam String w_id,@RequestParam String level){
        boolean is = recordService.isExistRecord(user, Integer.parseInt(w_id));
        System.out.println(user.getU_id()+"修改单词 "+w_id+"level -> " + level);
        if (is){
            boolean b = recordService.setRecord(user, Integer.parseInt(w_id), Integer.parseInt(level));
            return new Result(b? Code.UPDATE_OK:Code.UPDATE_ERR,level,b?"这次修改了level":"更改level失败");
        }else {
            boolean b = recordService.addRecord(user, Integer.parseInt(w_id), Integer.parseInt(level));
            return new Result(b? Code.UPDATE_OK:Code.UPDATE_ERR,null,b?"这次新增了记录":"新增记录失败");
        }
    }

    @GetMapping("/count/{u_id}")
    public Result getUserRecordCount(@PathVariable int u_id){
        System.out.println(u_id+"查询count");
        Map map = recordService.getLearnedAndGraspCount(u_id);
        if (map == null){
            return new Result(Code.GET_ERR,null,"获取了用户"+u_id+"的两个记录数");
        }
        return new Result(Code.GET_OK,map,"获取了用户"+u_id+"的两个记录数");

    }
}
