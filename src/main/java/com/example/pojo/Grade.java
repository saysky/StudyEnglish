package com.example.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Grade {
    private Integer gradeId;
    private String gradeName;

    /**
     * 单词列表
     */
    private List<Word> wordList;
}
