package com.as.seckill.aop;

import com.as.seckill.annotation.SetRepHeaderACAO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Slf4j
public class SetRepHeaderACAOHandler {

    @Pointcut("@annotation(com.as.seckill.annotation.SetRepHeaderACAO)")
    public void pointcut(){}


    @Before("execution(* com.as.seckill.controller..*.*(..)) && pointcut()")
    public void setHeader(JoinPoint point){
//        HttpServletResponse response = (HttpServletResponse) point.getArgs()[1];
//        MethodSignature methodSignature = (MethodSignature) point.getSignature();
//        SetRepHeaderACAO acao = methodSignature.getMethod().getAnnotation(SetRepHeaderACAO.class);
//        String value = acao.value();
//        if("*".equals(value)){
//            response.setHeader("Access-Control-Allow-Origin", value);
//        }else{
//
//        }
//        String [] arr = AccessControlAllowOrigin.split(",");


    }
}
