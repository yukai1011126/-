package com.gdaas.iard.datafill.wechat.common;

import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;

public class WechatApiFallbackFactory implements FallbackFactory<WechatApiService> {

    @Override
    public WechatApiService create(Throwable throwable) {
        return new WechatApiService() {

            @Override
            public String jscode2session(String appid, String secret, String js_code, String grant_type) {
                JSONObject jsonObject= new JSONObject();
                jsonObject.put("code",404);
                jsonObject.put("message","服务暂时不可用了！");
                return jsonObject.toJSONString();
            }

            @Override
            public String ddd(String messageBody) {

                return null;
            }
        };
    }
}
