package com.example.mapper;

import com.example.pojo.Book;
import com.example.pojo.Grade;
import com.example.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface BookMapper {
    //根据ID查询一条书的信息
    Book queryBookById(Integer bookId);
    //增加书
    int addBook(Book book);
    //删除书
    int deleteBook(Integer bookId);
    //修改书
    int updateBook(Book book);
    //查看书所有信息
    List<Book> queryAllBook();

    int countByGrade(Integer grade);


    /**
     * 查询下一个
     * @return
     */
    Book getNext(Integer taskId);

    List<Grade> getAllGrade();

}
