package com.gdaas.iard.datafill.wechat.common;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信 api 调用
 *
 * wechat.api.url
 */

@FeignClient(value = "WechatApiService",url = "https://api.weixin.qq.com",
        fallbackFactory = WechatApiFallbackFactory.class,
        configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface WechatApiService {
//    获取用户的 openId
//    /sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
    @GetMapping("/sns/jscode2session")
    String jscode2session(@RequestParam(value = "appid", required = true) String appid,
                              @RequestParam(value = "secret", required = true) String secret,
                              @RequestParam(value = "js_code", required = true) String js_code,
                              @RequestParam(value = "authorization_code", required = true) String grant_type
                                    );

    @RequestMapping("/msg_notify/send")
    String ddd(@RequestBody  String messageBody);
}
