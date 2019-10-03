/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;

/**
 * <p> 服务类</p>
 *
 * @author like
 * @since 2019-09-24
 */
public interface TUserService extends BaseService<TUserEntity> {

    /**
     * 检查微信用户登录
     */
    JSONObject   checkWechatUserLogin(  String code);

    /**
     *  微信用户登录
     */
    JSONObject   wechatUserLogin(String mobile,String password);


    /**
     *  微信用户登录
     */
    JSONObject   register( TUserEntity entity,String code);









}
