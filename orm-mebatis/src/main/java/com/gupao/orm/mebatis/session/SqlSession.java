package com.gupao.orm.mebatis.session;

import com.gupao.orm.mebatis.executor.Executor;

/**
 * @Author laihui
 * @Date 2019/6/28
 * @Desc
 * @Version 1.0
 **/
public class SqlSession {
    // 配置信息的封装
    private Configuration configuration;

    // 数据库连接信息的封装
    private Executor executor;
}
