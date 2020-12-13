package com.example.service.Impl;

import com.example.mapper.NoticeMapper;
import com.example.pojo.Notice;
import com.example.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Notice queryNoticeById(Integer noticeId) {
        return noticeMapper.queryNoticeById(noticeId);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int deleteNotice(Integer noticeId) {
        return noticeMapper.deleteNotice(noticeId);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public List<Notice> queryAllNotice() {
        return noticeMapper.queryAllNotice();
    }

    @Override
    public Notice queryNewNoticeById() {
        return noticeMapper.queryNewNoticeById();
    }
}
