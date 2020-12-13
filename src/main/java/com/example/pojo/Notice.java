package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.Format;
import java.util.Date;

@Data
public class Notice {
    private Integer noticeId;
    private String title;
    private String content;
    @DateTimeFormat(pattern="yyyy-MM-dd",iso=DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date creatTime;

}
