package com.example.controller;

import com.example.dto.LetterDTO;
import com.example.pojo.*;
import com.example.service.*;
import com.example.service.Impl.NoticeServiceImpl;
import com.example.service.Impl.SentenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class MainPageController {

    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private SentenceServiceImpl sentenceService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private WordService wordService;

    @Autowired
    private BookService bookService;


    @Autowired
    private ListenService listenService;

    public final String LETTERS_OF_26 = "abcdefghijklmnopqrstuvwxyz";


    /**
     * 公共的，方法执行前执行
     *
     * @param model
     */
    @ModelAttribute
    public void populateModel(Model model) {
        // ---->   以下为公共的
        //随机查询每日一句
        Sentence sentence = sentenceService.queryRandomSentence();
        model.addAttribute("sentence", sentence);

        // 字母词汇表
        List<LetterDTO> letterList = new ArrayList<>();
        letterList.add(new LetterDTO("a"));
        letterList.add(new LetterDTO("b"));
        letterList.add(new LetterDTO("c"));
        for (LetterDTO letterDTO : letterList) {
            letterDTO.setWordList(wordService.findByInitWord(letterDTO.getLetter(), 20));
        }

        model.addAttribute("letterList", letterList);
    }


    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@RequestParam(required = false) Integer gradeId, Model model) {


        // 词汇表
        List<Grade> gradeList = new ArrayList<>();
        if (gradeId != null) {
            Grade grade = gradeService.queryGradeById(gradeId);
            if (grade == null) {
                return "redirect:/";
            }
            gradeList.add(grade);
        } else {
            gradeList = gradeService.queryAllGrade();
        }
        for (Grade temp : gradeList) {
            List<Word> wordList = wordService.queryByGradeId(temp.getGradeId(), 20);
            temp.setWordList(wordList);
        }
        model.addAttribute("gradeList", gradeList);
        return "index";
    }


    /**
     * 字母列表
     *
     * @return
     */
    @GetMapping("/word/letter/all")
    public String wordLetterList(Model model) {

        List<LetterDTO> allLetterList = new ArrayList<>();
        for (int i = 0; i < LETTERS_OF_26.length(); i++) {
            LetterDTO letterDTO = new LetterDTO();
            String letter = LETTERS_OF_26.charAt(i) + "";
            letterDTO.setLetter(letter);
            letterDTO.setWordList(wordService.findByInitWord(letterDTO.getLetter(), 20));
            allLetterList.add(letterDTO);
        }

        model.addAttribute("allLetterList", allLetterList);

        return "wordLetterAll";
    }


    /**
     * 单个字母
     *
     * @return
     */
    @GetMapping("/word/letter/{letter}")
    public String singleLetter(@PathVariable("letter") String letter, Model model) {
        LetterDTO letterDTO = new LetterDTO();
        letterDTO.setLetter(letter);
        letterDTO.setWordList(wordService.findByInitWord(letterDTO.getLetter(), null));
        model.addAttribute("letter", letterDTO);

        return "wordLetterDetail";
    }


    /**
     * 单个字母
     *
     * @return
     */
    @GetMapping("/word/{wordId}")
    public String singleLetter(@PathVariable("wordId") Integer wordId, Model model) {
        Word word = wordService.queryWordById(wordId);
        if (word == null) {
            return "redirect:/";
        }
        model.addAttribute("word", word);

        return "wordDetail";
    }

    /**
     * 书籍列表
     *
     * @return
     */
    @GetMapping("/book/all")
    public String bookList(Model model) {

        List<Book> bookList = bookService.queryAllBook();
        model.addAttribute("bookList", bookList);
        return "bookList";
    }

    /**
     * 书籍列表
     *
     * @return
     */
    @GetMapping("/book/{bookId}")
    public String bookDetail(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = bookService.queryBookById(bookId);
        if (book == null) {
            return "redirect:/";
        }
        book.setContent(book.getContent().replace("\r\n", "<br/>").replace("\n", "<br/>"));
        model.addAttribute("book", book);
        return "bookDetail";
    }


    /**
     * 听力列表
     *
     * @return
     */
    @GetMapping("/listen/all")
    public String listenList(Model model) {

        List<Listen> listenList = listenService.queryAllListen();
        model.addAttribute("listenList", listenList);
        return "listenList";
    }

    /**
     * 听力列表
     *
     * @return
     */
    @GetMapping("/listen/{listenId}")
    public String listenDetail(@PathVariable("listenId") Integer listenId, Model model) {
        Listen listen = listenService.queryListenById(listenId);
        if (listen == null) {
            return "redirect:/";
        }
        listen.setContent(listen.getContent().replace("\r\n", "<br/>").replace("\n", "<br/>"));
        model.addAttribute("listen", listen);
        return "listenDetail";
    }

    /**
     * 名人名言列表
     *
     * @return
     */
    @GetMapping("/sentence/all")
    public String sentenceList(Model model) {
        List<Sentence> sentenceList = sentenceService.queryAllSentence();
        model.addAttribute("sentenceList", sentenceList);
        return "sentenceList";
    }

    /**
     * 名人名言列表
     *
     * @return
     */
    @GetMapping("/notice/all")
    public String noticeList(Model model) {
        List<Notice> noticeList = noticeService.queryAllNotice();
        model.addAttribute("noticeList", noticeList);
        return "noticeList";
    }

    /**
     * 名人名言列表
     *
     * @return
     */
    @GetMapping("/search")
    public String sentenceList(@RequestParam String keywords, Model model) {
        List<Word> wordList = wordService.queryWordByName(keywords);
        List<Grade> gradeList = gradeService.queryAllGrade();

        for (Word word : wordList) {
            for (Grade grade : gradeList) {
                if (Objects.equals(word.getGrade(), grade.getGradeId())) {
                    if (grade.getWordList() == null) {
                        grade.setWordList(new ArrayList<>());
                    }
                    grade.getWordList().add(word);
                }
            }
        }
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("keywords", keywords);
        return "wordSearchResult";
    }


}
