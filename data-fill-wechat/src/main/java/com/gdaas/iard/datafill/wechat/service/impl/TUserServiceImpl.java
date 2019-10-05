/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdaas.iard.datafill.common.BaseCode;
import com.gdaas.iard.datafill.common.util.IDGenerate;
import com.gdaas.iard.datafill.common.util.SecurityUtil;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import com.gdaas.iard.datafill.wechat.service.TUserService;
import com.gdaas.iard.datafill.wechat.miniprogram.WechatApiService;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TUserDao;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

/**
 * <p> 服务实现类</p>
 *
 * @author like
 * @since 2019-09-24
 */

@Log4j2
@Service
public class TUserServiceImpl extends AbstractBaseService<TUserDao, TUserEntity>
        implements TUserService {
    @Autowired
    WechatApiService wechatApiService;
    @Autowired
    TBaseConfigServiceImpl baseConfigService;

    @Value("${wechat.appId}")
    String appId;

    @Value("${wechat.appSecret}")
    String appSecret;


    String authorization_code = "authorization_code";

    @Transactional
    @Override
    public JSONObject checkWechatUserLogin(String code) {
        JSONObject result = new JSONObject();
        JSONObject json = new JSONObject();
        try {
            String ss = wechatApiService.jscode2session(appId, appSecret, code, authorization_code);
            json = JSON.parseObject(ss);

            String openid = json.getString("openid");
            String session_key = json.getString("session_key");
            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");

            result.put("errmsg", errmsg);
//        返回成功 验证openId   是否在用户表中
            if (!StringUtils.isEmpty(openid)) {
                result.put("openId", openid);
                QueryWrapper<TUserEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(TUserEntity::getOpenId, openid);
                List<TUserEntity> list = this.list(queryWrapper);
                if (!list.isEmpty()) {
                    TUserEntity entity = list.get(0);
//                登录成功
                    result.put("sessionKey", session_key);
                    result.put("id", entity.getId());
                    result.put("avatarUrl", entity.getAvatarUrl());
                    result.put("nickName", entity.getNickName());
                }else{
                    result.put("code", BaseCode.UNAUTHORIZED.getCode());
                    result.put("msg", BaseCode.UNAUTHORIZED.getMsg());
                }
            }
        } catch (Exception e) {
            result.put("code", BaseCode.UNAUTHORIZED.getCode());
            result.put("msg", BaseCode.UNAUTHORIZED.getMsg());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 用户登录操作
     * @param mobile
     * @param password
     * @return
     */
    @Transactional
    @Override
    public JSONObject wechatUserLogin(String mobile, String password) {
        JSONObject result = new JSONObject();

        try {
//                码处理
                QueryWrapper<TUserEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(TUserEntity::getMobile, mobile);
                List<TUserEntity> list = this.list(queryWrapper);
                if (!list.isEmpty()) {
                    TUserEntity entity = list.get(0);
//               有用户验证密码 登录成功
                    String enPassword= SecurityUtil.sha256Encrypt(password+mobile+entity.getNaCl());
                    if(enPassword.equalsIgnoreCase(entity.getPassword())){
                        result.put("id", entity.getId());
                        result.put("avatarUrl", entity.getAvatarUrl());
                        result.put("openId", entity.getOpenId());
                        result.put("nickName", entity.getNickName());
                        result.put("code", BaseCode.USER_LOGINED.getCode());
                        result.put("msg", BaseCode.USER_LOGINED.getMsg());
                    }else{
                        result.put("code", BaseCode.PASSWORD_ERROR.getCode());
                        result.put("msg", BaseCode.PASSWORD_ERROR.getMsg());
                    }
                }else{
                    result.put("code", BaseCode.UNREGISTER.getCode());
                    result.put("msg", BaseCode.UNREGISTER.getMsg());
                }
        } catch (Exception e) {
            result.put("code", BaseCode.UNREGISTER.getCode());
            result.put("msg", BaseCode.UNREGISTER.getMsg());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 微信用户注册，根据code  拿到openID
     * @param entity
     * @param code
     * @return
     */
    @Transactional
    @Override
    public JSONObject register(TUserEntity entity,String code) {
        Faker faker=Faker.instance(Locale.CHINA);
        JSONObject result = new JSONObject();
        JSONObject json = new JSONObject();
        try{
//            检查 用户是否存在
            QueryWrapper<TUserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TUserEntity::getMobile, entity.getMobile());
            List<TUserEntity> list = this.list(queryWrapper);
            if (list.isEmpty()) {
//                拿用户的openId
                String ss = wechatApiService.jscode2session(appId, appSecret, code, authorization_code);
                json = JSON.parseObject(ss);
                String openid = json.getString("openid");
                String session_key = json.getString("session_key");
                String errcode = json.getString("errcode");
                String errmsg = json.getString("errmsg");
                if (!StringUtils.isEmpty(openid)) {
                    result.put("openId", openid);
                    entity.setOpenId(openid);
                    entity.setNaCl(faker.random().hex(20));
                    String enPassword= SecurityUtil.sha256Encrypt(entity.getPassword()+entity.getMobile()+entity.getNaCl());
                    entity.setPassword(enPassword);
                    entity.setId(IDGenerate.id());
                    entity.setStatus("0");//未审核状态
                    save(entity);
                    result.put("openId", openid);
                    result.put("id", entity.getId());
                    result.put("code", BaseCode.USRE_REGISTER_SUCCESSED.getCode());
                    result.put("msg", BaseCode.USRE_REGISTER_SUCCESSED.getMsg());
                }else{
                    result.put("code", BaseCode.USRE_REGISTER_ERROR.getCode());
                    result.put("msg", BaseCode.USRE_REGISTER_ERROR.getMsg());
                }
            }else{
                result.put("code", BaseCode.USRE_EXISTED.getCode());
                result.put("msg", BaseCode.USRE_EXISTED.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.put("code", BaseCode.USRE_REGISTER_ERROR.getCode());
            result.put("msg", BaseCode.USRE_REGISTER_ERROR.getMsg());
        }
        return result;
    }




}
