package com.czk.service.impl;

import com.czk.dao.wordsDao;
import com.czk.domain.Word;
import com.czk.service.wordService;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class wordServiceImpl implements wordService {

    @Autowired
    private wordsDao wordsDao;
    @Override
    public List<Word> getWordList() {
        return wordsDao.getWordList();
    }


    /*@Override
    public List<Word> getWordList() {
        System.out.println("Service");
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:json/CET4luan_1.json");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String s = null;
        try {
            s = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] strings = s.split("\n");
        List<Word> list = new ArrayList();
        for (int i=0;i<strings.length;i++) {
            JSONObject jsonObject = null;
            Integer w_id = null;
            String word = null;
            String tranCn = null;
            try {
                jsonObject = new JSONObject(strings[i]);
                w_id = Integer.valueOf(jsonObject.getString("wordRank"));
                word = jsonObject.getString("headWord");
                tranCn = jsonObject.getJSONObject("content").getJSONObject("word").getJSONObject("content").getJSONArray("trans").optJSONObject(0).getString("tranCn");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            list.add(new Word(w_id,word,tranCn));
        }
        return list;
    }*/
}
