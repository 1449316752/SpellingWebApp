package com.czk.dao.impl;

import com.czk.dao.wordsDao;
import com.czk.domain.Word;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class wordsDaoImpl implements wordsDao {
    @Override
    public List<Word> getWordList() {
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
    }
}
