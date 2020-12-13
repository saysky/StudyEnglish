package com.example.controller.admin;

import com.example.pojo.Book;
import com.example.service.Impl.BookServiceImpl;
import com.example.service.Impl.NoticeServiceImpl;
import com.example.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class UpdateBookController {
    @Autowired
    NoticeServiceImpl noticeService;
    @Autowired
    DataUtils dataUtils;
    @Autowired
    BookServiceImpl bookService;

    /**
     *
     * 查看书籍
     */
    @RequestMapping("/admin/viewBook")
    public String viewBook(Model model){
        List<Book> book = bookService.queryAllBook();
        model.addAttribute("book",book);
        return "admin/Book-View";
    }


    /**
     *
     *  修改书籍
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateBook/{bookId}")
    public String toUpdateBook(@PathVariable("bookId")Integer bookId,Model model){

        Book book = bookService.queryBookById(bookId);
        model.addAttribute("book",book);
        return "admin/Book-Update";
    }
    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateBook/{bookId}")
    public String updateBook(@PathVariable("bookId")Integer bookId,Book book){


        bookService.updateBook(book);
        return "redirect:/admin/viewBook";
    }
    /**
     *
     *  删除书籍
     */

    @RequestMapping("/admin/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer bookId){

        bookService.deleteBook(bookId);

        return "redirect:/admin/viewBook";
    }

    /**
     *
     *  增加书籍
     */

    @RequestMapping("/admin/toAddBook")
    public String toAddBook(){
        return "admin/Book-Add";
    }
    @PostMapping("/admin/addBook")
    public String addBook(Book book) throws ParseException {


        bookService.addBook(book);
        return "redirect:/admin/viewBook";
    }

}
