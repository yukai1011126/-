/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by Yimei Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.common;

import java.sql.RowId;

/**
 * <p>Description : 系统参数</p>
 * <p>Created on  : 2018-09-20 19:19</p>
 *
 * @author jerryniu
 * @since 1.0.0
 */
public final class SystemConstant {
    public static final String PROFILE_DEV = "dev";
    public static final String PROFILE_TEST = "test";
    public static final String PROFILE_PROD = "prod";
    //加解密(密钥)
    public static final String KEYT ="79e7c69681b8270162386e6daa53d1dc";
    //登陆超时时间
    public static final int SESSIONOUTTIME = 86400000;
    private SystemConstant() {
    }
}
