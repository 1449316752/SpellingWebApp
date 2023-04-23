package com.czk.domain;

public class WordListType {
    private int list_id;
    private String list_name;

    public WordListType() {
    }

    public WordListType(int list_id, String list_name) {
        this.list_id = list_id;
        this.list_name = list_name;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    @Override
    public String toString() {
        return "WordListType{" +
                "list_id=" + list_id +
                ", list_name='" + list_name + '\'' +
                '}';
    }
}
