package com.example.controller;

import com.example.pojo.User;
import com.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    private UserServiceImpl userService;

    /**
     * 普通用户登录
     * 登录成功，添加session
     */
    @RequestMapping("/doLogin")
    public String login(User user, Model model, HttpSession session) {
        User usr = userService.loginByUserNameAndPasswordAndActiveStatus(user);
        if (usr != null) {
            //设置session
            session.setAttribute("loginUser", usr);
            System.out.println("成功登录");

            if (user.getRole() == 1) {
                return "redirect:/admin";
            } else {
                return "redirect:/userCenter";
            }
        } else {
            model.addAttribute("msg", "密码或账号输入错误");
            return "login";
        }
    }

    /**
     * 管理员用户登录
     */
    @RequestMapping("/toAdmin")
    public String toAdmin() {
        return "admin/index";
    }

    @RequestMapping("/admin")
    public String AdminLogin() {
        return "admin/main";
    }

    /**
     * 注销
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @GetMapping("/forget")
    public String forget() {
        return "forget";
    }


    /**
     * 跳转管理员主页
     */
    @RequestMapping("/admin/main")
    public String toAdminPage(Model model) {
        return "admin/main";
    }
}
