/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.web.common;

/**
 * <p>description : 基础回复编码</p>
 *
 * @author like
 * @since 2019-11-12
 */
public enum BaseCode {
    /**成功*/
    SUCCESS("1000", "成功"),
    /**失败*/
    FAIL("9999", "失败"),
    /**异常*/
    ERROR("0000", "未知错误,请联系管理员"),
    /**校验异常*/
    REVIEW("0001", "校验异常");

    /**回复码*/
    private String code;
    /**回复信息*/
    private String msg;

    BaseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * <p>description : 用回复码返回对应枚举类型</p>
     */
    public static BaseCode getBaseCodeByCode(String code) {
        for (BaseCode baseCode : BaseCode.values()) {
            if (baseCode.code.equals(code)) {
                return baseCode;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}