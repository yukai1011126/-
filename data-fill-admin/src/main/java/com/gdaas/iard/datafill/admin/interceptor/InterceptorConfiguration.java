package com.gdaas.iard.datafill.admin.interceptor;

import com.gdaas.iard.datafill.admin.util.FinalString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
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
	private UserLoginInterceptor userLoginInterceptor;
    @Autowired
    private ExceptionInterceptor exceptionInterceptor;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = {"/**/**/**.action","/**/**.action"};
    	// 用户登陆拦截器
    	registry.addInterceptor(userLoginInterceptor).addPathPatterns(FinalString.UNLOGININTERCEP).excludePathPatterns(FinalString.LOGININTERCEP);
    	//异常拦截器
        registry.addInterceptor(exceptionInterceptor).addPathPatterns(patterns);
    }
}


