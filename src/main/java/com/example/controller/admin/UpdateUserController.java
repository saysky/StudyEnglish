package com.example.controller.admin;

import com.example.pojo.User;
import com.example.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class UpdateUserController {
    @Autowired
    UserServiceImpl userService;

    /**
     * 查看用户
     */
    @RequestMapping("/admin/viewUser")
    public String viewUser(Model model) {
        List<User> user = userService.queryAllUser();
        model.addAttribute("user", user);
        return "admin/User-View";
    }


    /**
     * 修改用户
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateUser/{userId}")
    public String toUpdateUser(@PathVariable("userId") Integer userId, Model model) {

        User user = userService.queryUserById(userId);

        model.addAttribute("user", user);
        return "admin/User-Update";
    }

    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateUser/{userId}")
    public String updateUser(@PathVariable("userId") Integer userId, User user) {
        userService.updateUser(user);

        return "redirect:/admin/viewUser";
    }

    /**
     * 删除用户
     */

    @RequestMapping("/admin/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {

        userService.deleteUser(userId);


        return "redirect:/admin/viewUser";
    }

    /**
     * 增加用户
     */
//
    @RequestMapping("/admin/toAddUser")
    public String toAddUser() {
        return "admin/User-Add";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user) {
        userService.addUser(user);

        return "redirect:/admin/viewUser";
    }
