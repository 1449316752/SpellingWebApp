package com.czk.service.impl;

import com.czk.dao.WordsDao;
import com.czk.domain.User;
import com.czk.domain.Word;
import com.czk.domain.WordListType;
import com.czk.domain.WordRecord;
import com.czk.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class wordServiceImpl implements WordService {

    @Autowired
    private WordsDao wordsDao;
    @Override
    public List<Word> getWords(int list_id) {
        return wordsDao.getWords(list_id);
    }

    @Override
    public List<Word> getWordsAndRecord(int u_id, int list_id) {
        return wordsDao.getWordsAndRecord(u_id,list_id);
    }

    @Override
    public List<WordListType> getWordList() {
        return wordsDao.getWordList();
    }

    @Override
    public List<WordRecord> getWordRecords(User user) {
        return wordsDao.getRecords(user);
    }

    @Override
    public List<Word> getForgetWords(int u_id, int list_id, int type) {
        return wordsDao.getForgetWords(u_id, list_id, type);
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
