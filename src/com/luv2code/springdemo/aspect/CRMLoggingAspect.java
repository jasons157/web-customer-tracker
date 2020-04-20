package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations for anything in controller package
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage(){

    }

    //For service package
    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage(){

    }

    //For DAO package
    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage(){

    }

    //All packages except entity
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        //display method we're calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("----->> in @Before: calling method: " + method);


        //get the args
        Object[] args = joinPoint.getArgs();

        //loop through and display args
        for (Object tempArg: args){
            logger.info("=====>> Argument: " + tempArg);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        //display method we are returning from
        logger.info("@AfterReturning from method: " + joinPoint.getSignature().toShortString());

        //display data returned
        logger.info("=====>> result: " + result);
    }
}
