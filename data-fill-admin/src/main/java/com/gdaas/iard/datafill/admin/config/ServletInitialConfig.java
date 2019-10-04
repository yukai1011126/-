/*
 * Copyright 2018-2050 the original author or authors.
 * Powered by Yimei Corp.
 * All Rights Reserved.
 */

package com.gdaas.iard.datafill.admin.config;

import com.gdaas.iard.datafill.admin.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description : servlet配置,打war包使用</p>
 * <p>Created on  : 2018-10-11 12:57</p>
 *
 * @author jerryniu
 * @since 1.0.0
 */
@Configuration
public class ServletInitialConfig extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
