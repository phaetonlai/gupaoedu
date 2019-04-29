package com.gupao.gp17190.demo.aspect;

import com.gupao.gp17190.springframework.aop.aspect.MyJoinPoint;

import javax.sql.rowset.Joinable;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @Author laihui
 * @Date 2019/4/29
 * @Desc
 * @Version 1.0
 **/
public class LogAspect {
    private static final Logger logger = Logger.getLogger(LogAspect.class.getName());

    public void before(MyJoinPoint joinPoint) {
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(), System.currentTimeMillis());
        //
        logger.info("Before Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
    }

    public void after(MyJoinPoint joinPoint) {
        logger.info("After Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
        Long startTime = (Long) joinPoint.getUserAttribute("\"startTime_\" + joinPoint.getMethod().getName()");
        Long endTime = System.currentTimeMillis();
        logger.info("executing time:" + (endTime - startTime));
    }

    public void afterThrowing(MyJoinPoint joinPoint, Throwable e) {
        logger.info("After Throwing!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()) +
                "\nThrows:" + e.getMessage());
    }
}
