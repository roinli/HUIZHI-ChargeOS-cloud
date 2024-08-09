package com.jingli.inter.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsDto implements Serializable {

    private Long id;

    private String title;

    private String name;

    private String type;

    private String content;


    private String status;

    private String sort;

    private String configKey;

    private String remark;


}
