package com.gdaas.iard.datafill.wechat.web.common;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class BaseRequest {
    Integer page=1;
    Integer rows=10;
    ConcurrentHashMap<String,Object> param;

}
