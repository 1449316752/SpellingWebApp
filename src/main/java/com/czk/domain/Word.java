package com.czk.domain;

public class Word {
    private int w_id;
    private String word;
    private String tranCn;
    private int level;
    private boolean isGrasp;

    public Word(int w_id, String word, String tranCn, int level, boolean isGrasp) {
        this.w_id = w_id;
        this.word = word;
        this.tranCn = tranCn;
        this.level = level;
        this.isGrasp = isGrasp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isGrasp() {
        return isGrasp;
    }

    public void setIgnore(boolean ignore) {
        isGrasp = ignore;
    }

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
    public String
    toString() {
        return "Word{" +
                "w_id=" + w_id +
                ", word='" + word + '\'' +
                ", tranCn='" + tranCn + '\'' +
                ", level=" + level +
                ", isGrasp=" + isGrasp +
                '}';
    }
}
