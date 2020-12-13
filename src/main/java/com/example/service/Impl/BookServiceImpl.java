package com.example.service.Impl;

import com.example.mapper.BookMapper;
import com.example.pojo.Book;
import com.example.pojo.Grade;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public Book queryBookById(Integer bookId) {
        return bookMapper.queryBookById(bookId);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(Integer bookId) {
        return bookMapper.deleteBook(bookId);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<Book> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    @Override
    public int countByGrade(Integer grade) {
        return bookMapper.countByGrade(grade);
    }

    @Override
    public Book getNext(Integer taskId) {
        return bookMapper.getNext(taskId);
    }

    @Override
    public List<Grade> getAllGrade() {
        return bookMapper.getAllGrade();
    }
}
