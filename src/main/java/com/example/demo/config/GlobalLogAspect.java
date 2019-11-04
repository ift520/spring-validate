package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liufei
 * @since 2019-11-01 10:21
 */
@Aspect
@Component
@Slf4j
public class GlobalLogAspect {



    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void pointcut() {}

    @AfterReturning(value = "pointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String className = method.getDeclaringClass().getTypeName();
        String methodName = className + "." + method.getName();
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer =
                new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        log.info("method：{} parameterNames：{} args：{} result: {}",
                methodName, Arrays.toString(parameterNames), Arrays.toString(args), result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String className = method.getDeclaringClass().getTypeName();
        String methodName = className + "." + method.getName();
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer =
                new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        log.error("method：{} parameterNames：{} args：{} ex：{} e: {}",
                methodName, Arrays.toString(parameterNames), Arrays.toString(args), e.getMessage(), e);

    }
}
