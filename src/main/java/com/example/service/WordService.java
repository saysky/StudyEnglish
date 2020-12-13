package com.example.service;

import com.example.pojo.Grade;
import com.example.pojo.Word;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface WordService {


    //增加一个单词
    int addWord(Word word);

    //删除一个单词
    int deleteWord(Integer wordId);

    //修改一个单词
    int updateWord(Word word);

    //查询所有单词信息
    List<Word> queryAllWord();

    //根据ID查询一个单词的信息
    Word queryWordById(Integer wordId);

    /**
     * 单词搜索
     *
     * @param name
     * @return
     */
    List<Word> queryWordByName(String name);


    //根据grade查询单词数量
    int queryAllWordNumberByGrade(Integer grade);

    List<Word> queryByGradeId(Integer gradeId, Integer limit);

    /**
     * 首字母查询
     *
     * @param initWord
     * @return
     */
    List<Word> findByInitWord(String initWord, Integer limit);

    Word getNext(Integer taskId);

    List<Grade> getAllGrade();

}
