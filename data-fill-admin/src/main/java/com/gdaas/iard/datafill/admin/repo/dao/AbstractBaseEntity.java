/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by LIKE Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.repo.dao;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * <p>基础实体</p>
 *
 * @author like
 * @since 2019-10-04
 */
public abstract class AbstractBaseEntity<T extends Model> extends Model<T> {
    private String id;

    private Date createTime;

    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}