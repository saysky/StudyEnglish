package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    //测试MarkDown编辑器！
    @RequestMapping("/test")
    public String Test(){
        System.out.println("进入TEST！！");
        return "Test/ToHTMLTest";
    }
}
