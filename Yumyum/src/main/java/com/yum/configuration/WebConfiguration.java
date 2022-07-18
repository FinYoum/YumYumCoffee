package com.yum.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
 	private String connectPath = "/imagePath/**";
 	//경로 신경쓰시오!!
    //private String resourcePath = "file:///Users/user/OneDrive/사진/yumyum/";
    private String resourcePath = "file:///Users/"+System.getProperty("user.name")+"/Pictures/yumyum/";


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }

}
