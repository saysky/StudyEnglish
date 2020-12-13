package com.example.controller.admin;

import com.example.pojo.Notice;
import com.example.service.Impl.NoticeServiceImpl;
import com.example.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class UpdateNoticeController {
    @Autowired
    NoticeServiceImpl noticeService;
    @Autowired
    DataUtils dataUtils;

    /**
     *
     * 查看公告
     */
    @RequestMapping("/admin/viewNotice")
    public String viewNotice(Model model){
        List<Notice> notice = noticeService.queryAllNotice();
        model.addAttribute("notice",notice);
        return "admin/Notice-View";
    }


    /**
     *
     *  修改公告
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateNotice/{noticeId}")
    public String toUpdateNotice(@PathVariable("noticeId")Integer noticeId,Model model){

        Notice notice = noticeService.queryNoticeById(noticeId);
        model.addAttribute("notice",notice);
        return "admin/Notice-Update";
    }
    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateNotice/{noticeId}")
    public String updateNotice(@PathVariable("noticeId")Integer noticeId,Notice notice){

        Date creatTime = notice.getCreatTime();
        System.out.println(creatTime);
       noticeService.updateNotice(notice);
       return "redirect:/admin/viewNotice";
    }
    /**
     *
     *  删除公告
     */

    @RequestMapping("/admin/deleteNotice/{noticeId}")
    public String deleteNotice(@PathVariable("noticeId") Integer noticeId){

        noticeService.deleteNotice(noticeId);

        return "redirect:/admin/viewNotice";
    }

    /**
     *
     *  增加公告
     */

    @RequestMapping("/admin/toAddNotice")
    public String toAaddNotice(){
        return "admin/Notice-Add";
    }
    @PostMapping("/admin/addNotice")
    public String addNotice(Notice notice) throws ParseException {
        //得到当前日期
        Date date = dataUtils.getDate();
        notice.setCreatTime(date);

        noticeService.addNotice(notice);
        return "redirect:/admin/viewNotice";
    }

}
