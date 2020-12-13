package com.example.controller;

import com.example.pojo.User;
import com.example.service.Impl.SendEmailImpl;
import com.example.service.Impl.UserServiceImpl;
import com.example.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class ForgetController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SendEmailImpl sendEmail;


    //进行注册
    @RequestMapping("/doForget")
    public String register(User user, Model model) {
        // 判断用户或者邮箱是否存在
        User check = userService.findByUserNameAndEmail(user);
        if (check == null) {
            model.addAttribute("msg", "用户名和邮箱不匹配");
            return "forget";
        }
        //发送邮件激活
        String email = user.getEmail();
        String subject = "来自学英语网网站的密码重置";
        String newPassword = UUID.randomUUID().toString().substring(0, 8);
        check.setPassword(newPassword);
        userService.updateUser(check);

        String context = "您的密码已经被重置为" + newPassword;
        sendEmail.SendEmail(email, subject, context);
        //回到主页面给用户提示
        model.addAttribute("msg", "您的密码已经发送到邮箱中");
        return "register";
    }


}
