package com.gdaas.iard.datafill.admin.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置类
 * @author Lenovo
 *
 */
@Component
public class CorsRegistryConfiguration extends WebMvcConfigurerAdapter{
	public void addCorsMappings(CorsRegistry registrys){
		registrys.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
				.maxAge(3600)
				.allowCredentials(true);
		
	}
}


