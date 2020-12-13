package com.example.pojo;

import lombok.Data;

@Data
public class Task {
    private Integer id;
    private String type;
    private Integer grade;
    private Integer userId;
    private String name;

    /**
     * 完成百分比
     */
    private String percent;
    private Integer total;
    private Integer finish;
}
