package com.example.mapper;

import com.example.pojo.Grade;
import com.example.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WordMapper {


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

    //根据grade查询单词数量
    int queryAllWordNumberByGrade(Integer grade);


    List<Word> queryByGradeId(@Param("gradeId") Integer gradeId,
                              @Param("limit") Integer limit);


    /**
     * 首字母查询
     *
     * @param initWord
     * @param limit
     * @return
     */
    List<Word> findByInitWord(@Param("initWord") String initWord,
                              @Param("limit") Integer limit);

    /**
     * 查询下一个单词
     *
     * @return
     */
    Word getNext(Integer taskId);

    List<Grade> getAllGrade();


    List<Word> queryWordByName(String name);
}
