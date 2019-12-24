package com.gdaas.iard.datafill.admin.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置类
 * @author Lenovo
 *
 */
@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter{
    @Autowired
    private ExceptionInterceptor exceptionInterceptor;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = {"/**/**/**.action","/**/**.action"};
    	// 用户登陆拦截器
        // 用户登陆拦截
       /* public static final String[] UNLOGININTERCEP = {"/TAreaDepartmentController/*","/TAreaDepartmentRoleController/*","/TAreaDepartmentRoleUserController/*","/TBaseConfigController/*","/TCommAreaController/*","/TUserController/*"
                ,"/TDataDictController/*","/TDepartmentController/*","/TNoticeController/*","/TRoleController/*",     "/TTaskAreaController/*","/TTaskAuditLogController/*","/TTaskController/*","/TTemplateController/*","/TTemplateDataController/*","/TTemplateTaskController/*"};
        // 用户登陆放行拦截
        public static final String[] LOGININTERCEP = {"/unlogin/*"};*/
    	//registry.addInterceptor(userLoginInterceptor).addPathPatterns(FinalString.UNLOGININTERCEP).excludePathPatterns(FinalString.LOGININTERCEP);
    	//异常拦截器
        registry.addInterceptor(exceptionInterceptor).addPathPatterns(patterns);
    }
}


