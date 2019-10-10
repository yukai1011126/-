package com.gdaas.iard.datafill.admin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gdaas.iard.datafill.admin.repo.dao.entity.Users;
import com.gdaas.iard.datafill.admin.service.TUserService;
import com.gdaas.iard.datafill.admin.util.FinalString;
import com.gdaas.iard.datafill.admin.util.JwtUtil;
import com.gdaas.iard.datafill.admin.util.SingletonEnum;
import com.gdaas.iard.datafill.common.BaseResp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@CrossOrigin
@RequestMapping("/unlogin")
@Controller
public class UnLoginController {
	
	private static SimpleDateFormat simp = SingletonEnum.SIMPLEDATE.getSimpleDate();
	@Autowired
	private TUserService targetService;
	/**
	 * 登陆
	 * @param
	 * @return InfoMessage
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResp login(@RequestBody Users usertrmp) throws Exception {
		String username = usertrmp.getUsername();
		String password = usertrmp.getPassword();
		long time = new Date().getTime()+Long.valueOf(FinalString.SESSIONOUTTIME);
		try {
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				return BaseResp.fail("账号密码为空");
			}
			// 验证用户
			if (checkedUser(username,password)) {
				return BaseResp.fail("用户信息有误");
			}
			
			//加密生成token
			String token= JwtUtil.createJWT(username+"-"+time);
			return BaseResp.success(token);
			
		} catch (Exception e) {
			log.error("用户登陆出错：{}",e);
			return BaseResp.fail("后台用户登陆出错");
		}
	}
	
	private boolean checkedUser(String username,String password){
		log.info("当前登陆用户信息：username:{};password:{}",username,password);
		return false;
	}

}
