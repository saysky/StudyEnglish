package com.example.controller;

import com.example.pojo.*;
import com.example.service.*;
import com.example.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserCenterController {


    @Autowired
    private WordService wordService;

    @Autowired
    private BookService bookService;


    @Autowired
    private ListenService listenService;

    @Autowired
    private TaskService taskService;

    public static Map<Integer, String> gradeMap = new HashMap<>();

    static {
        gradeMap.put(1, "四级");
        gradeMap.put(2, "六级");
        gradeMap.put(3, "雅思");
        gradeMap.put(4, "托福");
    }


    public static Map<String, String> typeMap = new HashMap<>();

    static {
        typeMap.put("book", "阅读");
        typeMap.put("listen", "听力");
        typeMap.put("word", "单词");
    }


    /**
     * 用户中心
     *
     * @return
     */
    @GetMapping("/userCenter")
    public String manage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);

        // 学习进度
        List<Task> taskList = taskService.findByUserId(user.getUserId());
        for (Task task : taskList) {
            Integer total = 0;
            if ("word".equals(task.getType())) {
                total = wordService.queryAllWordNumberByGrade(task.getGrade());
            } else if ("book".equals(task.getType())) {
                total = bookService.countByGrade(task.getGrade());
            } else if ("listen".equals(task.getType())) {
                total = listenService.countByGrade(task.getGrade());
            }
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            task.setPercent(MathUtil.toPercent(finish, total));
        }

        model.addAttribute("taskList", taskList);
        return "userCenter";
    }

    /**
     * 选择页面
     *
     * @return
     */
    @GetMapping("/userCenter/{type}")
    public String manage(@PathVariable("type") String type, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);
        List<Grade> gradeList = new ArrayList<>();
        if ("book".equals(type)) {
            gradeList = bookService.getAllGrade();
        } else if ("listen".equals(type)) {
            gradeList = listenService.getAllGrade();
        } else if ("word".equals(type)) {
            gradeList = wordService.getAllGrade();
        }
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("type", type);
        model.addAttribute("typeName", typeMap.get(type));
        return "userCenterSelect";
    }

    /**
     * 选择提交
     *
     * @return
     */
    @GetMapping("/userCenter/doSelect")
    public String doSelect(@RequestParam("grade") Integer grade,
                           @RequestParam("type") String type, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        Task task = new Task();
        task.setType(type);
        task.setGrade(grade);
        task.setUserId(user.getUserId());
        task.setName(typeMap.get(type) + gradeMap.get(grade));
        taskService.addTask(task);
        return "redirect:/userCenter";
    }

    /**
     * 取消进度
     *
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/userCenter/task/cancel")
    public String cancelTask(@RequestParam("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }

        Task task = taskService.queryTaskById(id);
        if (task == null || !Objects.equals(task.getUserId(), user.getUserId())) {
            return "redirect:/userCenter";
        }
        // 删除任务进度
        taskService.deleteTask(id);

        return "redirect:/userCenter";
    }

    /**
     * 完成一个
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/task/finishDetail")
    public String finishTaskDetail(@RequestParam("taskId") Integer taskId,
                                   @RequestParam("businessId") Integer businessId,
                                   HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }


        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setBusinessId(businessId);
        taskDetail.setTaskId(taskId);
        taskService.addTaskDetail(taskDetail);
        return "redirect:/userCenter/task/" + taskId;
    }

    /**
     * 取消完成一个
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/task/cancelFinishDetail")
    public String cancelFinishTaskDetail(@RequestParam("taskId") Integer taskId,
                                         @RequestParam("businessId") Integer businessId,
                                         HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }

        taskService.deleteTaskDetail(taskId, businessId);
        return "redirect:/userCenter/task/" + taskId;
    }

    /**
     * 用户中心
     *
     * @return
     */
    @GetMapping("/userCenter/task/{taskId}")
    public String taskDetail(@PathVariable("taskId") Integer taskId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);

        // 学习进度
        Task task = taskService.queryTaskById(taskId);
        if (task == null) {
            return "redirect:/";
        }

        if ("word".equals(task.getType())) {
            Integer total = wordService.queryAllWordNumberByGrade(task.getGrade());
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            if (finish.equals(total)) {
                return "redirect:/userCenter";
            }
            // 查询未完成的，下一个
            Word word = wordService.getNext(taskId);
            if (word == null) {
                return "redirect:/userCenter";
            }

            model.addAttribute("word", word);
            model.addAttribute("businessId", word.getWordId());
            task.setPercent(MathUtil.toPercent(finish, total));

            model.addAttribute("task", task);
            model.addAttribute("isTask", true);
            return "wordDetail";
        } else if ("book".equals(task.getType())) {
            Integer total = bookService.countByGrade(task.getGrade());
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            if (finish.equals(total)) {
                return "redirect:/userCenter";
            }
            // 查询未完成的，下一个
            Book book = bookService.getNext(taskId);
            if (book == null) {
                return "redirect:/userCenter";
            }
            book.setContent(book.getContent().replace("\r\n", "<br/>").replace("\n", "<br/>"));

            model.addAttribute("businessId", book.getBookId());
            model.addAttribute("book", book);
            task.setPercent(MathUtil.toPercent(finish, total));
            model.addAttribute("task", task);

            model.addAttribute("isTask", true);
            return "bookDetail";
        } else if ("listen".equals(task.getType())) {
            Integer total = listenService.countByGrade(task.getGrade());
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            if (finish.equals(total)) {
                return "redirect:/userCenter";
            }
            // 查询未完成的，下一个
            Listen listen = listenService.getNext(taskId);
            if (listen == null) {
                return "redirect:/userCenter";
            }
            listen.setContent(listen.getContent().replace("\r\n", "<br/>").replace("\n", "<br/>"));

            model.addAttribute("listen", listen);
            model.addAttribute("businessId", listen.getListenId());

            task.setPercent(MathUtil.toPercent(finish, total));
            model.addAttribute("task", task);

            model.addAttribute("isTask", true);
            return "listenDetail";
        }
        return "redirect:/userCenter";
    }


}
