package com.example.service.Impl;

import com.example.mapper.SentenceMapper;
import com.example.pojo.Sentence;
import com.example.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SentenceServiceImpl implements SentenceService {
    @Autowired
    SentenceMapper sentenceMapper;

    @Override
    public Sentence querySentenceById(Integer sentenceId) {
        return sentenceMapper.querySentenceById(sentenceId);
    }

    @Override
    public int addSentence(Sentence sentence) {
        return sentenceMapper.addSentence(sentence);
    }

    @Override
    public int deleteSentence(Integer sentenceId) {
        return sentenceMapper.deleteSentence(sentenceId);
    }

    @Override
    public int updateSentence(Sentence sentence) {
        return sentenceMapper.updateSentence(sentence);
    }

    @Override
    public List<Sentence> queryAllSentence() {
        return sentenceMapper.queryAllSentence();
    }

    @Override
    public Sentence queryRandomSentence() {
        return sentenceMapper.queryRandomSentence();
    }
}
