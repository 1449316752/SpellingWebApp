package com.czk.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class CountLog {
    private int id;
    private int u_id;
    private Date date;
    private int wordCount;
    private int learnCount;

}
