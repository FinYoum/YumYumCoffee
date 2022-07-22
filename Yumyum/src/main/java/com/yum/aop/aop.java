package com.yum.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class aop {
//	// com.yum.controller 패키지의 하위 메소드를 Aspect로 설정
//	// LoginController 제외
//	
//    @Pointcut("execution(* com.yum.controller.*.*(..)) "
//    		+ "and !execution(com.yum.controller.LoginController.*(..))")
//    private void cut() {
//
//    }
////  LoginController의 logout메서드 추가
//    @Pointcut("execution(* com.yum.controller.LoginController.logout(..)) ")
//    private void logout() {
//
//    }
//    
//
//    // cut() 메소드가 실행되는 지점의 이전에 before() 메소드 수행
//    @Before("cut() || logout()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("--------------- @Before ---------------");
//        System.out.println(joinPoint.toString());
//        System.out.println(joinPoint.toShortString());
//        System.out.println(joinPoint.toLongString());
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        System.out.println("method: " + method.getName());
//
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            System.out.println("type: " + arg.getClass().getSimpleName());
//            System.out.println("value: " + arg);
//        }
//    }
//
//    // 반환값 확인
//    @AfterReturning(value = "cut()", returning = "object")
//    public void afterReturn(JoinPoint joinPoint, Object object) {
//        System.out.println("--------------- @After ---------------");
//        System.out.println(object);
//    }
}
