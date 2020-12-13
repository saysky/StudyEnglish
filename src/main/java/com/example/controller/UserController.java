package com.example.controller;

import com.example.pojo.User;
import com.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //用户自己修改名字
    //接收前端传来的字符串
    @RequestMapping("/getStringParam/{newname}")
    public String getStringParam(@PathVariable("newname") String newName, HttpSession session) {
        //从session中拿到用户，修改session的姓名
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setUserName(newName);
        //修改数据库中的名字
        userService.updateUser(loginUser);


        return "redirect:/userCenter";
    }

    //前往用户修改信息页面
    @RequestMapping("/toUser")
    public String toUser(HttpSession session, Model model) {
        //从session中拿到用户，修改session的姓名
        User loginUser = (User) session.getAttribute("loginUser");

        model.addAttribute("loginUser", loginUser);


        return "user/user";
    }

    //用户修改信息
    @RequestMapping("/userUpdate/{userId}")
    public String userUpdate(@PathVariable("userId") Integer userId, User user, HttpSession session, Model model) {

        //修改信息
        userService.updateUser(user);

        //设置session
        session.setAttribute("loginUser", user);

        return "redirect:/userCenter";
    }
}
