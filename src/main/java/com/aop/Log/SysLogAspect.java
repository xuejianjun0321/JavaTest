package com.aop.Log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SysLogAspect {


    //切入点
    @Pointcut(value = "@annotation(com.aop.Log.SysLog)")
    private void pointcut() {

    }


    /**
     * 在方法执行前后
     *
     * @param point
     * @param myLog
     * @return
     */
    @Around(value = "pointcut() && @annotation(myLog)")
    public Object around(ProceedingJoinPoint point, SysLog myLog) {

        System.out.println("++++执行了around方法++++");

        String requestUrl = myLog.requestUrl();

        //拦截的类名
        Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        System.out.println("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     *
     * @param joinPoint
     * @param myLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(myLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, SysLog myLog, Object result) {

        System.out.println("++++执行了afterReturning方法++++");

        System.out.println("执行结果：" + result);

        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(myLog)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, SysLog myLog, Exception ex) {
        System.out.println("++++执行了afterThrowing方法++++");
        System.out.println("请求：" + myLog.requestUrl() + " 出现异常");
    }
}
