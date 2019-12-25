package com.gdaas.iard.datafill.common;

import lombok.Data;


@Data
public class BaseRequest<T> {
    Integer page=1;
    Integer rows=20;
    String code;
    // 排序
    String sort;
    // 模糊查询key
    String vague;
    // 状态查询
    String status;
    T param;
    String date;

}
