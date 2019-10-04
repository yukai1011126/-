package com.gdaas.iard.datafill.wechat;


import com.gdaas.iard.datafill.common.util.SecurityUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        String mobile="15921262959";
        String password="888888";
        String nacl="salt";

        System.out.println(SecurityUtil.sha256Encrypt(password+mobile+nacl));
    }

}
