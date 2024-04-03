package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class TimeLogAspect {

   @Around("execution(* org.example.service.*.*(..))")
//    @Around("@annotation(org.example.LogTimeExecution)")
    public Object logTimeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("*****************\n+time: " + (end - start) + "ms\n**************************");

        return proceed;
    }

    @AfterReturning(value = "@annotation(org.example.LogTimeExecution)", returning = "result")
    public void afterReturn(List result){
       if(result instanceof List){
           List items = (List) result;
           for (int i = 0; i < items.size(); i++) {
               System.out.println((i+1) +") "+ items.get(i));
           }
       }
    }

    @AfterThrowing(value = "@annotation(org.example.LogTimeExecution)", throwing = "ex")
    public void afterThrowingException(Exception ex){
        System.out.println("_________________");
       if(ex.getCause()==null){
           System.err.println(ex.getCause());
       }
    }
}
