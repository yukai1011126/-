/*
 * Copyright 2018-2050 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.like.mpcreator.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.like.mpcreator.common.StringUtil;
import com.like.mpcreator.configure.ResourceUtil;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * <p>Description : 多模块项目配置类</p>
 * <p>Created on  : 2018-09-12 16:36</p>
 *
 * @author jerryniu
 * @since 1.0.0
 */
public class MultiModulePathConfig implements MpgPathConfig {
    private static final String CONFIG_FILE_PATH = "creed-mybatis-plus";

    private static final String GROUPID_KEY = "groupId";
    private static final String ARTIFACTID_KEY = "artifactId";
    private static final String PACKAGE_KEY = "package";
    private static final String AUTHOR_KEY = "author";
    private static final String DATABASE_DRIVER_KEY = "mysql.driver";
    private static final String DATABASE_URL_KEY = "mysql.url";
    private static final String DATABASE_USER_KEY = "mysql.user";
    private static final String DATABASE_PASSWORD_KEY = "mysql.pwd";

    private static final String ROOTARTIFACTDIR_KEY = "rootArtifactDir";
    private static final String HEADER_KEY = "header";
    private static final String OVERRIDE_KEY = "override";
    private static final String INCLUDE_TABLES_KEY = "includeTables";

    private String groupId;
    private String artifactId;
    private String pkg;
    private String author;
    private String databaseDriver;
    private String databaseUrl;
    private String databaseUser;
    private String databasePassword;

    private String rootArtifactDir;
    private String header = "/*\n"
            + " * Copyright 2018-2050 the original author or authors.\n"
            + " * Powered by Yimei Corp.\n"
            + " * All Rights Reserved.\n"
            + " */";
    /**执行时判断*/
    private boolean override = true;
    private String[] includeTables = {};

    /**
     * <p>description : 初始化多模块路径配置</p>
     * <p>create   on : 2018-09-12 19:36:23</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public MpgPathConfig init() {
        Map<String, String> properties = ResourceUtil.readPropertiesFromResources(CONFIG_FILE_PATH);
        groupId = properties.get(GROUPID_KEY);
        artifactId = properties.get(ARTIFACTID_KEY);
        pkg = properties.get(PACKAGE_KEY);
        if (StringUtil.isEmpty(pkg)) {
            pkg = groupId + StringPool.DOT + artifactId;
        }
        author = properties.get(AUTHOR_KEY);
        databaseDriver = properties.get(DATABASE_DRIVER_KEY);
        databaseUrl = properties.get(DATABASE_URL_KEY);
        databaseUser = properties.get(DATABASE_USER_KEY);
        databasePassword = properties.get(DATABASE_PASSWORD_KEY);

        rootArtifactDir = properties.get(ROOTARTIFACTDIR_KEY);
        if (StringUtil.isEmpty(rootArtifactDir)) {
            rootArtifactDir = new File("").getAbsoluteFile().getParent();
        }
        if (!rootArtifactDir.endsWith(File.separator)) {
            rootArtifactDir += File.separator;
        }

        String tmpHeader = properties.get(HEADER_KEY);
        if (!StringUtil.isEmpty(tmpHeader)) {
            header = tmpHeader;
        }
        String overrideStr = properties.get(OVERRIDE_KEY);
        if (!StringUtil.isEmpty(overrideStr)) {
            override = Boolean.parseBoolean(overrideStr);
        }
        String includeTablesStr = properties.get(INCLUDE_TABLES_KEY);
        if (!StringUtil.isEmpty(includeTablesStr)) {
            includeTables = includeTablesStr.split(",", -1);
        }
        return this;
    }

    /**
     * <p>description : 获取父模块根目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getRootArtifactDir() {
        return rootArtifactDir;
    }

    private String getRepoRoot() {
        return rootArtifactDir + artifactId + "-repo";
    }

    private String getServiceRoot() {
        return rootArtifactDir + artifactId + "-service";
    }

    private String getWebRoot() {
        return rootArtifactDir + artifactId + "-web";
    }

    /**
     * <p>description : 获取entity目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getEntityDir() {
        return getRepoRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator
                + "repo" + File.separator + "dao" + File.separator + "entity";
    }

    /**
     * <p>description : 获取dao接口目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getDaoDir() {
        return getRepoRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator
                + "repo" + File.separator + "dao" + File.separator + "mapper";
    }

    @Override
    public String getRespDir() {
        return getWebRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator + "web" + File.separator + "common";
    }

    /**
     * <p>description : 获取dao根目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getBaseDaoDir() {
        return getRepoRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator
                + "repo" + File.separator + "dao";
    }

    /**
     * <p>description : 获取xml目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getXmlDir() {
        return getRepoRoot() + File.separator + "src" + File.separator + "main" + File.separator + "resources"
                + File.separator + "mapper";
    }

    /**
     * <p>description : 获取service目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getServiceDir() {
        return getServiceRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator + "service";
    }

    /**
     * <p>description : 获取serviceImpl目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getServiceImplDir() {
        return getServiceRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator + "service" + File.separator + "impl";
    }

    /**
     * <p>description : 获取controller目录</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getControllerDir() {
        return getWebRoot() + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + getPkgDir() + File.separator + "web" + File.separator + "controller";
    }

    private String getPkgDir() {
        String pkgDir = pkg.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        return pkgDir;
    }

    /**
     * <p>description : 获取根package</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getPkg() {
        return pkg;
    }

    /**
     * <p>description : 获取作者名</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * <p>description : 获取文件头</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getHeader() {
        return header;
    }

    /**
     * <p>description : 获取数据库驱动类名</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getDatabaseDriver() {
        return databaseDriver;
    }

    /**
     * <p>description : 获取数据库地址</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    /**
     * <p>description : 获取数据库用户名</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * <p>description : 获取数据库密码</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String getDatabasePassword() {
        return databasePassword;
    }

    /**
     * <p>description : 是否覆盖原文件</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public boolean isOverride() {
        return override;
    }

    /**
     * <p>description : 包含哪些表</p>
     * <p>create   on : 2018-09-12 19:36:42</p>
     *
     * @author jerryniu
     * @version 1.0.0
     */
    @Override
    public String[] getIncludeTables() {
        return includeTables;
    }
}
