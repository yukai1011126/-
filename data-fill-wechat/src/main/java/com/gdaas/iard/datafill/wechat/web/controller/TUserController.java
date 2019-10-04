/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.BaseResp;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TUserEntity;
import com.gdaas.iard.datafill.wechat.service.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p> 前端控制器</p>
 *
 * 增POST 删DELETE 改PUT 查GET
 * CRUD (POST GET PUT DELETE)
 *
 * @author like
 * @since 2019-09-24
 */
@Log4j2
@Api(tags = "TUserController")
@RestController("TUserController")
@RequestMapping("/user")
public class TUserController {
    @Autowired
    private TUserService targetService;

    /**
     * 根据ID查找数据
     *
     * @author jerryniu
     */
    @ApiOperation("查询单条记录")
    @GetMapping("/find")
    public BaseResp find(Long id) {
        TUserEntity entity = targetService.getById(id);
        if (entity == null) {
            return BaseResp.fail("尚未查询到此ID");
        }
        return BaseResp.success(entity);
    }


    /**
     * 用户登录
     * @author  LIKE
     */
    @ApiOperation("用户登录")
    @PostMapping  (value = "/login")
    public BaseResp login(@RequestBody TUserEntity request) {
        log.info(request);
        JSONObject jsonObject=targetService.wechatUserLogin(request.getMobile(),request.getPassword());
        BaseResp res=   BaseResp.success();
        res.setData(jsonObject);
        return res;
    }

    /**
     * 检查用户是否合法用户，如果不是返回错误代码
     *
     * @author  LIKE
     */
    @ApiOperation(" 根据 code 检查用户是否合法用户，如果不是返回错误代码")
    @PostMapping  (value = "/checkUser")
    public BaseResp checkUser(@RequestBody BaseRequest<HashMap> request) {
        log.info(JSONObject.toJSONString(request));

        String code=request.getParam().get("code").toString();
        JSONObject jsonObject=targetService.checkWechatUserLogin(code);
        BaseResp res=   BaseResp.success();
        res.setData(jsonObject);
        return res;
    }



    /**
     * 用户注册
     *
     * @author like
     */
    @ApiOperation("用户注册")
    @PutMapping(value = "/register")
    public BaseResp register(@RequestBody BaseRequest<TUserEntity> request) {
//        System.out.println(JSONObject.toJSONString(request));
        log.info(JSONObject.toJSONString(request));

        try{
            JSONObject jsonObject= targetService.register(request.getParam(),request.getCode());
            BaseResp res=   BaseResp.success();
            res.setData(jsonObject);
            return res;
        }catch (Exception e){
            return BaseResp.fail("数据更改失败");
        }
    }

}
