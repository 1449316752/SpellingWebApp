package com.czk.domain;

public class Word {
    private int w_id;
    private String word;
    private String tranCn;

    public Word() {
    }

    public Word(int w_id, String word, String tranCn) {
        this.w_id = w_id;
        this.word = word;
        this.tranCn = tranCn;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranCn() {
        return tranCn;
    }

    public void setTranCn(String tranCn) {
        this.tranCn = tranCn;
    }

    @Override
    public String toString() {
        return "Word{" +
                "w_id=" + w_id +
                ", word='" + word + '\'' +
                ", tranCn='" + tranCn + '\'' +
                '}';
    }
}
