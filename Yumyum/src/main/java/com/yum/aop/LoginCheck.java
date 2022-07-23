package com.yum.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.yum.constant.SessionConstants;

@Component
@Aspect
public class LoginCheck {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    
//  cut(), logout()의  메소드가 실행되는 지점의 이전에 Before()내의 메소드 수행
//	execution(리턴타입 패키지.*.*(..)) >> * 모든 파일, (..)0개이상의 파라미터를 가진 메소드
//  com.yum.controller 패키지의 하위 메소드를 Aspect로 설정
//	LoginController 제외
//  LoginController의 logout메서드 추가
	
    @Around("execution(* com.yum.controller.*.*(..)) "
    		+ "and !execution(* com.yum.controller.LoginController.*(..))"
    		+ "and execution(* com.yum.controller.LoginController.logout())")
    public Object loginCheck(ProceedingJoinPoint  joinPoint) throws Throwable {
//	매개변수로 HttpSession이나 HttpServletRequest를 넣으면 null값이 전달되어 에러 발생
    	logger.debug("AOP 로그인 체크 진입");
    	
//    	MemberDTO를 사용하는 controller의 매개변수/파라미터로 (HttpSession session)지정
//      이 값을 obj를 통해 가져올 수 있음. obj는 원래 이 함수가 받은 매개변수

		//값을 안쓰는 컨트롤러라면 에러가나겠네. 그러니 try로 감싸주면되겠네.
    	
    	Object[] obj = joinPoint.getArgs();
		for (Object ele : obj) {
			if (ele instanceof HttpSession) {
				HttpSession session = (HttpSession)ele;
				logger.debug("세션인스턴스 잘가져옴");
				logger.debug(""+session.getAttribute(SessionConstants.loginMember));
				return joinPoint.proceed();
			} 
		}	
//		Object objAfterAop = joinPoint.proceed();
//		ModelAndView mv = (ModelAndView)objAfterAop;
//		mv.setViewName("alert");
//		mv.addObject("message", "잘못된 경로입니다.");
//		mv.addObject("redirectRUL","login/login");
		logger.debug("세션이 없습니다.");
		return joinPoint.proceed();

	}
}

