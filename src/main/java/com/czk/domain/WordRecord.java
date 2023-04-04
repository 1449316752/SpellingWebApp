package com.czk.domain;

public class WordRecord {
    private int w_id;
    private int level;
    private boolean isGrasp;

    public WordRecord() {
    }

    public WordRecord(int w_id, int level, boolean isGrasp) {
        this.w_id = w_id;
        this.level = level;
        this.isGrasp = isGrasp;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
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

    @Override
    public String toString() {
        return "WordRecord{" +
                "w_id=" + w_id +
                ", level=" + level +
                ", isGrasp=" + isGrasp +
                '}';
    }
}
