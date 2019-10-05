package com.gdaas.iard.datafill.admin.interceptor;

import com.gdaas.iard.datafill.admin.service.TUserService;
import com.gdaas.iard.datafill.admin.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Log4j2
@Component
public class UserLoginInterceptor implements HandlerInterceptor{

	@Autowired
	private TUserService userservice;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3){
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3){
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		log.info("拦截------------------------");
		//创建session
		try {
			String token =  arg0.getHeader("token");
			if(StringUtils.isEmpty(token)){
				return checkfail(arg0,arg1);
			}
			String[] trmp = JwtUtil.parseJWT(token).split("-");
			if(trmp == null
					|| userservice.getById(trmp[1]) == null
					|| new Date().getTime() > Long.valueOf(trmp[1])){
				return checkfail(arg0,arg1);
			}
		}catch (Exception e){
			log.error(e);
		}
		return true;
	}
	
	private boolean checkfail(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception{
		//arg0.getRequestDispatcher("/unlogin/loginfail").forward(arg0, arg1);
		return true;
	}
	
}
