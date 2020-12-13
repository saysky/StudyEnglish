package com.example.pojo;

import lombok.Data;

@Data
public class Word {
    private Integer wordId;
    private String wordName;
    private String audio;
    private String explanation;
    private String example;
    private Integer grade;
    private Integer study;
    private Integer remember;
    private Integer collection;
}
