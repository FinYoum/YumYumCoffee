package com.yum.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yum.interceptor.CartInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CartInterceptor())
		.excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
	
		// 참고 : https://congsong.tistory.com/24 
	}
	
//	  /*
//     * 로그인 인증 Interceptor 설정
//     * */
//    @Autowired
//    CertificationInterceptor certificationInterceptor;
//    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(certificationInterceptor)
//                .addPathPatterns("/**/*.do");
//    }
		// 참조 : https://www.leafcats.com/40
	
}
