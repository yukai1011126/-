/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TAreaDepartmentRoleUserEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TCommAreaEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.entity.TTaskReleaseEntity;
import com.gdaas.iard.datafill.wechat.repo.dao.mapper.TTaskReleaseDao;
import com.gdaas.iard.datafill.wechat.service.TAreaDepartmentRoleUserService;
import com.gdaas.iard.datafill.wechat.service.TCommAreaService;
import com.gdaas.iard.datafill.wechat.service.TTaskReleaseService;
import com.gdaas.iard.datafill.wechat.service.AbstractBaseService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>任务发布表 服务实现类</p>
 *
 * @author like
 * @since 2019-10-08
 */
@Service
public class TTaskReleaseServiceImpl extends AbstractBaseService<TTaskReleaseDao, TTaskReleaseEntity>
        implements TTaskReleaseService {



    @Autowired
    TAreaDepartmentRoleUserService areaDepartmentRoleUserService;

    @Autowired
    TCommAreaService commAreaService;

    /**
     * 根据用户查询其所有的用户任务
     * 1 根据用户查询到的所以在机构，再查询机构的任务。
     *
     * @param userId
     * @return
     */
    public JSONObject queryProcess(String userId) {
        JSONObject result = new JSONObject();
        try {
//          1   根据用户查询到的所以在机构
            QueryWrapper<TAreaDepartmentRoleUserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TAreaDepartmentRoleUserEntity::getUserId,userId);
            List<TAreaDepartmentRoleUserEntity> list=areaDepartmentRoleUserService.list();
            List<String> areaids=new ArrayList();
            for (TAreaDepartmentRoleUserEntity userEntity : list) {
                areaids.add(userEntity.getAreaId());
            }
            /*  查询 状态*/
            if(! areaids.isEmpty()){
                List<Map<String,Object>>  listresult=getListBySqlId("queryProcess",areaids);
                result.put("records",listresult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据用户查询其所有的用户任务
     * 1 根据用户查询到的所以在机构，再查询机构的任务。
     *
     * @param userId
     * @return
     */
    public JSONObject queryProcess2(String userId,String taskId) {
        JSONObject result = new JSONObject();
        try {
//          1   根据用户查询到的所以在机构
            QueryWrapper<TAreaDepartmentRoleUserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TAreaDepartmentRoleUserEntity::getUserId,userId);
            List<TAreaDepartmentRoleUserEntity> list=areaDepartmentRoleUserService.list();
            List<String> areaids=new ArrayList();
            for (TAreaDepartmentRoleUserEntity userEntity : list) {
                areaids.add(userEntity.getAreaId());
            }
            /*  查询子机构下的后有机构*/

            if(! areaids.isEmpty()){
                List<String> temp=new Vector<>();
                areaids.forEach(areaid->{
                    QueryWrapper<TCommAreaEntity> queryWrapper1=new QueryWrapper<>();
                    queryWrapper1.lambda().like(TCommAreaEntity::getPath,areaid);
                    List<TCommAreaEntity> areas=commAreaService.list(queryWrapper1);
                    for (TCommAreaEntity a : areas) {
                        temp.add(a.getId());
                    }
                });
                QueryWrapper<TTaskReleaseEntity> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.lambda()
                        .in(TTaskReleaseEntity::getAreaId,temp)
                         .eq(TTaskReleaseEntity::getTaskId,taskId);
                List<TTaskReleaseEntity> records=list(queryWrapper2);
                result.put("records",records);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
