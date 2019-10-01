package com.gdaas.iard.datafill.wechat;


import com.gdaas.iard.datafill.wechat.web.common.BaseResp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @GetMapping("/test")
    public BaseResp test(){
        BaseResp baseResp=new BaseResp();
        baseResp.setData("success");
        return baseResp;
    }
}
