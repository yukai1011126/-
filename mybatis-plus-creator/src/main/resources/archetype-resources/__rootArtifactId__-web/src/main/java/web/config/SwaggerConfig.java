#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by Yimei Corp.
 * All Rights Reserved.
 */

package ${package}.web.config;

import ${package}.web.common.SystemConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Description : swagger配置类,在开发环境与测试环境开启文档</p>
 * <p>Created on  : 2018-08-27 20:38</p>
 *
 * @author jerryniu
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
@Profile({SystemConstant.PROFILE_DEV, SystemConstant.PROFILE_TEST})
public class SwaggerConfig {

    public static final String GROUP_NAME = "all";
    public static final String VERSION = "1.0.0";

    /**
     * <p>description : </p>
     * <p>create   on : 2018-08-30 18:06:19</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME)
                .apiInfo(apiInfo())
                .select()
                //扫描注解了@ApiOperation的方法生成API接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档的标题
                .title("Swagger2接口文档")
                //设置文档的描述
                .description("Swagger2接口文档")
                //设置文档的版本信息
                .version(VERSION)
                //设置文档的联系方式
                .contact(new Contact("jerryniu", "https://about.me/jerryniu", "huifumanlove@hotmail.com"))
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
