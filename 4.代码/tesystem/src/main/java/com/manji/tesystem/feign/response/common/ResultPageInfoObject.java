package com.manji.tesystem.feign.response.common;

import lombok.Data;

import java.util.List;

@Data
public class ResultPageInfoObject<T> {
    private PageModel page;
    private List<T> list;
}
