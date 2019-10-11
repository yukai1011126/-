package com.gdaas.iard.datafill.admin.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdaas.iard.datafill.admin.repo.dao.AbstractBaseEntity;
import com.gdaas.iard.datafill.common.BaseRequest;
import com.gdaas.iard.datafill.common.util.IDGenerate;

import java.text.ParseException;
import java.util.Date;

public class MyUtil {

    /**
     * 为分页属性添加参数与排序
     * @param param
     * @return
     */
    public static Page pageDecorate(BaseRequest param){
        Page page = new Page(param.getPage(), param.getRows());
        String sort = param.getSort();
        if( sort.contains("+")){
            page.setAsc(sort.replace("+",""));
        }else if (sort.contains("-")){
            page.setDesc(sort.replace("-",""));
        }
        return page;
    }

    /**
     * 对修改或创建进行时间与id的装饰
     * @param body 实体类
     * @param isok 区分保存或修改
     * @return 实体类
     * @throws ParseException
     */
    public static AbstractBaseEntity addOrEditDecorate(AbstractBaseEntity body, boolean isok) {
        Date time = null;
        try {
            time = SingletonEnum.SIMPLEDATE.getSimpleDate().parse(SingletonEnum.SIMPLEDATE.getSimpleDate().format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(isok){
            body.setId(IDGenerate.id());
            body.setCreateTime(time);
        }
        body.setUpdateTime(time);
        return body;
    }
}
