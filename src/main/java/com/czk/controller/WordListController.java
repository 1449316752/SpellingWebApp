package com.czk.controller;

import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordListType;
import com.czk.service.RecordService;
import com.czk.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/word")
public class WordListController {

    @Autowired
    private WordService wordService;

    @Autowired
    private RecordService recordService;

    /**
     * 获取单词本，并对有记录的单词添加记录
     * @param u_id
     * @return
     */
    @GetMapping
    public Result getWords(@RequestParam int u_id,@RequestParam int list_id){
        List<Word> words = wordService.getWordsAndRecord(u_id,list_id);
        return new Result(Code.GET_OK,words,"获取单词本"+list_id+"成功");
    }


    @GetMapping("/wordlist")
    public Result getWordList(){
        List<WordListType> wordList = wordService.getWordList();
        if(wordList == null){
            return Result.Error("获取单词表失败");
        }
        return Result.Success(wordList,"获取单词表成功");
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
        if (Integer.parseInt(level) <-1 || Integer.parseInt(level) > 3){
            return new Result(Code.UPDATE_ERR,null,"新增记录失败,超出范围");
        }
        boolean is = recordService.isExistRecord(user, Integer.parseInt(w_id));
        System.out.println(user.getU_id()+"修改单词 "+w_id+"level -> " + level);
        if (is){
            boolean b = recordService.setRecord(user, Integer.parseInt(w_id), Integer.parseInt(level));
            return new Result(b? Code.UPDATE_OK:Code.UPDATE_ERR,level,b?"设置成功":"更改level失败");
        }else {
            boolean b = recordService.addRecord(user, Integer.parseInt(w_id), Integer.parseInt(level));
            return new Result(b? Code.UPDATE_OK:Code.UPDATE_ERR,null,b?"设置成功":"新增记录失败");
        }
    }

    /**
     * 目前弃用，获取用户的以学单词数和
     * @param u_id
     * @return
     */
    @GetMapping("/count/{u_id}")
    public Result getUserRecordCount(@PathVariable int u_id){
        System.out.println(u_id+"查询count");
        Map map = recordService.getLearnedAndGraspCount(u_id);
        if (map == null){
            return new Result(Code.GET_ERR,null,"获取了用户"+u_id+"的两个记录数");
        }
        return new Result(Code.GET_OK,map,"获取了用户"+u_id+"的两个记录数");
    }

    /**
     * 查询单词本的同时获取用户的记录，并且只获取 有生词记录的 或 有忽略词记录的，（由type参数决定，1为生词 ，2为忽略词）
     * @param u_id
     * @param list_id
     * @return
     */
    @GetMapping("/getForgetWords")
    public Result getForgetWords(@RequestParam int u_id,@RequestParam int list_id,@RequestParam int type){
        List<Word> wordList = wordService.getForgetWords(u_id, list_id, type);
        if (wordList == null){
            return Result.Success("","没有哦");
        }
        return Result.Success(wordList,type == 1 ? "获取生词成功":"获取忽略词成功");
    }

    /**
     * 修改用户单词记录的，修改isgrasp值，也就是设置用户的单词为普通词、生词、忽略词
     * @return
     */
    @PutMapping("/setRecordIsgrasp")
    public Result setRecordIsgrasp(@RequestBody User user,@RequestParam int w_id,@RequestParam int type){
        if (type != 0 && type != 1 && type != 2){
            return Result.Error("出错了，不要乱搞");
        }
        boolean flag =  recordService.setRecordIsgrasp(Integer.parseInt(user.getU_id()), w_id, type);
        if (flag){
            return Result.Success("修改成功");
        }
        recordService.addRecord(user, w_id, 0);
        flag =  recordService.setRecordIsgrasp(Integer.parseInt(user.getU_id()), w_id, type);
        if (flag){
            return Result.Success("修改成功");
        }
        return Result.Error("出错，可能是你乱搞");
    }

    /**
     * 单词学习count加1
     * @param user
     * @param w_id
     * @return
     */
    @PutMapping("/setRecordCount/{w_id}")
    public Result setRecordCount(@RequestBody User user,@PathVariable int w_id){
        Integer count = recordService.getRecordCount(Integer.parseInt(user.getU_id()),w_id);
        //有记录就+1
        if (count != null){
            boolean flag1 = recordService.setRecordCountAdd(Integer.parseInt(user.getU_id()),w_id,count+1);
            if (flag1){
                return Result.Success();
            }else {
                return Result.Error("出现未知错误");
            }
        }
        //没有记录，先创建记录
        boolean flag2 = recordService.addRecord(user, w_id, 0);
        if (flag2){
            boolean flag1 = recordService.setRecordCountAdd(Integer.parseInt(user.getU_id()),w_id,1);
            if (flag1){
                return Result.Success();
            }else {
                return Result.Error("出现未知错误");
            }
        }
        return Result.Error("出错，可能是你乱搞");
    }
}
