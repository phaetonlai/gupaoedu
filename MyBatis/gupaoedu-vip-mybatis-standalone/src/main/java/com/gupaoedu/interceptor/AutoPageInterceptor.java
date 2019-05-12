package com.gupaoedu.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/12
 * @description
 **/
@Intercepts({@Signature(type= Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class AutoPageInterceptor implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        // query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);// getBoundSql(Object parameterObject)
        RowBounds rowBounds = (RowBounds) args[2];

        if (rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        String sql = boundSql.getSql();
        String pagingSql = String.format(" LIMIT %d, %d", rowBounds.getOffset(), rowBounds.getLimit());
        pagingSql = sql + pagingSql;

        SqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), pagingSql, boundSql.getParameterMappings());

        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(mappedStatement, sqlSource);

        return invocation.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {

    }

}
