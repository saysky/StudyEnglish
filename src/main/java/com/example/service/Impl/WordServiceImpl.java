package com.example.service.Impl;

import com.example.mapper.WordMapper;
import com.example.pojo.Grade;
import com.example.pojo.Word;
import com.example.service.WordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private WordMapper wordMapper;

    @Override
    public int addWord(Word word) {
        return wordMapper.addWord(word);
    }

    @Override
    public int deleteWord(Integer wordId) {
        return wordMapper.deleteWord(wordId);
    }

    @Override
    public int updateWord(Word word) {
        return wordMapper.updateWord(word);
    }


    @Override
    public List<Word> queryAllWord() {
        return wordMapper.queryAllWord();
    }

    @Override
    public Word queryWordById(Integer wordId) {
        return wordMapper.queryWordById(wordId);
    }

    @Override
    public List<Word> queryWordByName(String name) {
        return wordMapper.queryWordByName(name);
    }

    @Override
    public int queryAllWordNumberByGrade(Integer grade) {
        return wordMapper.queryAllWordNumberByGrade(grade);
    }

    @Override
    public List<Word> queryByGradeId(Integer gradeId, Integer limit) {
        return wordMapper.queryByGradeId(gradeId, limit);
    }

    @Override
    public List<Word> findByInitWord(String initWord, Integer limit) {
        return wordMapper.findByInitWord(initWord, limit);
    }

    @Override
    public Word getNext(Integer taskId) {
        return wordMapper.getNext(taskId);
    }

    @Override
    public List<Grade> getAllGrade() {
        return wordMapper.getAllGrade();
    }
}
