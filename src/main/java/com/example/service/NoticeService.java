package com.example.service;

import com.example.pojo.Notice;

import java.util.List;

public interface NoticeService {
    //查询公告
    Notice queryNoticeById(Integer noticeId);
    //增加公告
    int addNotice(Notice notice);
    //删除公告
    int deleteNotice(Integer noticeId);
    //修改公告
    int updateNotice(Notice notice);
    //查看公告所有信息
    List<Notice> queryAllNotice();
    //查看最新公告的内容
    Notice queryNewNoticeById();
}
