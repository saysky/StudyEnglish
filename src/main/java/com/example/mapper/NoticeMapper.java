package com.example.mapper;

import com.example.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface NoticeMapper {
    //根据ID查询一条公告
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
