package com.yum.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yum.domain.MemberDTO;
import com.yum.mapper.RegisterMapper;

@Controller
public class LoginController {
	
	@Autowired
	   private SqlSession sqlSession;
	
	@GetMapping(value = "/register.do")
	public String openRegister(Model model) {
		return "login/register";
	}
	
//	@RequestMapping(value="/signCheck",method=RequestMethod.POST,produces="application/jason;charset=utf-8")
//	public String signCheck(HttpServletRequest hsr) {
//		try {
//			MemberDTO params = new MemberDTO();
//			params.setName("김종주");
//			params.setId("jongju");
//			params.setTel((int) 01080127716);
//			params.setEmail("jongjoo15@gmail.com");
//			params.setBirth("jongjoo15@gmail.com");
//			params.setAuthority((int) 1);
//			boardMapper.insertBoard(params);
//			
//				String name=hsr.getParameter("name");
//			   	String id=hsr.getParameter("id");
//			   	int tel=Integer.parseInt(hsr.getParameter("tel"));
//				String email=hsr.getParameter("email");
//				String birth=hsr.getParameter("birth");
//				int authority=Integer.parseInt(hsr.getParameter("authority"));
//				
//			RegisterMapper Member=sqlSession.getMapper(RegisterMapper.class);
//			Member.signCheck(id, name, tel, email, birth, authority);
//
//			return "login/register";
//		} catch(Exception e) {
//			System.out.println(e.getMessage());
//			return "login/register";
//		}
//	}


}
