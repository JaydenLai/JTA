package com.hz.syxx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/6 23:30.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.hz.syxx.controller..*.*(..))")
    public void commonLog(){}

    @Pointcut("execution(* com.hz.syxx.controller..HelloWorldController.sayHi(..))")
    public void sayHiLog(){}

    @Pointcut("execution(* com.hz.syxx.controller..HelloWorldController.sayNothing(..))")
    public void sayNothingLog(){}

    @Before("commonLog()")
    public void logBefore(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        logger.info("Log before method: {}",signature.getName());
    }

    @After("commonLog()")
    public void logAfter(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        logger.info("Log After method: {}",signature.getName());
    }

    @Around("sayHiLog()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        logger.info("Log Around before method: {}",signature.getName());
        String returnStr = (String) proceedingJoinPoint.proceed();
        logger.info("Log Around after method: {}",signature.getName());
        return returnStr;
    }

    @AfterReturning(pointcut = "sayHiLog()", returning = "returnVal")
    public void logAfterReturning(JoinPoint joinPoint,String returnVal){
        logger.info("Log AfterReturning method: {}, return value: {}",joinPoint.getSignature().getName(),returnVal);
    }

    //generally should log info in class instead of aspect
    @AfterThrowing(pointcut = "sayNothingLog()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,Throwable e){
        logger.info("Log AfterThrowing method: {}, exception msg: {}",joinPoint.getSignature().getName(),e.getMessage());
    }

}
