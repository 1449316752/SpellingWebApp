package com.czk.controller;

import com.czk.domain.Word;
import com.czk.service.wordService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wordList")
public class wordListController {

    @Autowired
    private wordService wordService;

    @GetMapping
    public Result getWordList(){
        System.out.println("controller");
        List<Word> wordList = wordService.getWordList();
        return new Result(0,wordList,"ok");
    }
}
