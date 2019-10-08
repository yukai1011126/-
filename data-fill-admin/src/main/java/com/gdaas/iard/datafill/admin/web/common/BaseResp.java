/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>description : 基础回复类</p>
 *
 * @author like
 * @since 2019-10-05
 */
@ApiModel("回复基类")
@Data
public class BaseResp<T> {
    /**数据载体*/
    @ApiModelProperty("数据载体")
    private Map<String,Object> data;
    /**描述*/
    @ApiModelProperty("描述")
    private String msg;
    /**编码*/
    @ApiModelProperty("编码")
    private Integer code;
    /**数据内容*/
    @ApiModelProperty("数据")
    private T list;

    /**
     * <p>description : 默认成功(不返回数据)</p>
     */
    public static BaseResp success() {
        return BaseResp.discuss(BaseCode.SUCCESS, null);
    }

    /**
     * <p>description : 成功(返回数据)</p>
     */
    public static <T> BaseResp<T> success(T data) {
        return BaseResp.discuss(BaseCode.SUCCESS, data);
    }

    /**
     * <p>description : 成功(返回数据)带描述</p>
     */
    public static <T> BaseResp<T> success(String msg, T data) {
        return BaseResp.discuss(msg,BaseCode.SUCCESS, data);
    }

    /**
     * <p>description : 默认失败(不返回数据)</p>
     */
    public static BaseResp fail() {
        return BaseResp.discuss(BaseCode.FAIL, null);
    }

    /**
     * <p>description : 失败(返回数据)</p>
     */
    public static <T> BaseResp<T> fail(T data) {
        return BaseResp.discuss(BaseCode.FAIL, data);
    }

    /**
     * <p>description : 提示校验异常</p>
     */
    public static BaseResp review() {
        return BaseResp.discuss(BaseCode.REVIEW, null);
    }

    /**
     * <p>description : 提示校验异常(返回信息)</p>
     */
    public static BaseResp review(String msg) {
        return BaseResp.custom(BaseCode.REVIEW.getCode(), BaseCode.REVIEW.getMsg() + ":" + msg, null);
    }

    /**
     * <p>description : 提示校验异常(返回数据)</p>
     */
    public static <T> BaseResp<T> review(T data) {
        return BaseResp.discuss(BaseCode.REVIEW, data);
    }

    /**
     * <p>description : 错误</p>
     */
    public static BaseResp error() {
        return BaseResp.discuss(BaseCode.ERROR, null);
    }

    /**
     * <p>description : 自定义返回码与数据(需要在BaseCode中建立新的枚举类)</p>
     */
    public static <T> BaseResp<T> custom(BaseCode code, T data) {
        Map<String ,Object> map = new HashMap<>();
        BaseResp<T> resp = new BaseResp();
        map.put("code",code.getCode());
        map.put("msg",code.getMsg());
        map.put("data",data);
        resp.setData(map);
        return resp;
    }

    /**
     * <p>description : 自定义返回码与数据</p>
     */
    public static <T> BaseResp<T> custom(Integer code, String msg, T data) {
        Map<String ,Object> map = new HashMap<>();
        BaseResp<T> resp = new BaseResp();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data);
        resp.setData(map);
        return resp;
    }

    private static <T> BaseResp<T> discuss(BaseCode code, T data) {
        Map<String ,Object> map = new HashMap<>();
        BaseResp<T> resp = new BaseResp();
        map.put("code",code.getCode());
        map.put("msg",code.getMsg());
        map.put("data",data);
        resp.setData(map);
        return resp;
    }
    private static <T> BaseResp<T> discuss(String msg ,BaseCode code, T data) {
        Map<String ,Object> map = new HashMap<>();
        BaseResp<T> resp = new BaseResp();
        map.put("code",code.getCode());
        map.put("msg",msg);
        map.put("data",data);
        resp.setData(map);
        return resp;
    }
}