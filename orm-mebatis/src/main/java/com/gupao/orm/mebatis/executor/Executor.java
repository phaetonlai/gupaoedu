package com.gupao.orm.mebatis.executor;

import com.gupao.orm.mebatis.mapping.MappedStatement;
import com.gupao.orm.mebatis.session.ResultHandler;
import com.gupao.orm.mebatis.session.RowBounds;

import java.util.List;

/**
 * @Author laihui
 * @Date 2019/6/28
 * @Desc
 * @Version 1.0
 **/
public interface Executor {
    <E> List<E> query(MappedStatement ms, Object param, RowBounds rowBounds, ResultHandler resultHandler);
}
