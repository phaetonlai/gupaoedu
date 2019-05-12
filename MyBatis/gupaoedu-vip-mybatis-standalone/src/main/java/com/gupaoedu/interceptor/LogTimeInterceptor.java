package com.gupaoedu.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/12
 * @description
 **/
@Intercepts({@Signature(type= StatementHandler.class, method = "query",
        args = {Statement.class, ResultHandler.class})})
public class LogTimeInterceptor implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();

        String sql = boundSql.getSql();
        System.out.println("##################自定义插件：LogTimeInterceptor，获取到要执行的sql：\n" + sql);
        long start = System.currentTimeMillis();

        try {
            return invocation.proceed();
        } finally {
            System.out.println("####################sql执行完毕，耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {

    }

}
