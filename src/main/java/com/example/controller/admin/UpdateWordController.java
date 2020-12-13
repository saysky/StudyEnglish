package com.example.controller.admin;

import com.example.pojo.Word;
import com.example.service.Impl.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UpdateWordController {
    @Autowired
    WordServiceImpl wordService;

    /**
     * 查看单词
     */
    @RequestMapping("/admin/viewWord")
    public String viewWord(Model model) {
        List<Word> word = wordService.queryAllWord();
        model.addAttribute("word", word);
        return "admin/Word-View";
    }


    /**
     * 修改单词
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateWord/{wordId}")
    public String toUpdateWord(@PathVariable("wordId") Integer wordId, Model model) {


        Word word = wordService.queryWordById(wordId);
        model.addAttribute("word", word);
        return "admin/Word-Update";
    }

    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateWord/{wordId}")
    public String updateNotice(@PathVariable("wordId")Integer wordId,Word word){

       wordService.updateWord(word);
        return "redirect:/admin/viewWord";
    }
    /**
     *
     *  删除单词
     */

    @RequestMapping("/admin/deleteWord/{wordId}")
    public String deleteNotice(@PathVariable("wordId") Integer wordId){

        wordService.deleteWord(wordId);


        return "redirect:/admin/viewWord";
    }

    /**
     *
     *  增加单词
     */
//
    @RequestMapping("/admin/toAddWord")
    public String toAddWord(){
        return "admin/Word-Add";
    }
    @PostMapping("/admin/addWord")
    public String addWord(Word word) {
        wordService.addWord(word);
        return "redirect:/admin/viewWord";
    }
}
