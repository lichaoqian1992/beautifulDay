package com.manji.orService.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UploadVo {

    private String id;

    private String orPath;

    private Date orUploadTime;

    private String orPersonId;
}
