package com.gdaas.iard.datafill.wechat.web.common;

import lombok.Data;


@Data
public class BaseRequest<T> {
    Integer page=1;
    Integer rows=10;
    String code;
    T param;

}
