package com.yum.controller;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yum.SessionConstants;
import com.yum.domain.MemberDTO;
import com.yum.service.MemberService;

@Controller
/* @RequestMapping("/yum") */
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	/* private SqlSession sqlSession; */
	
	
	@GetMapping(value = "/login")
	public String getLogin() {
		return "login/login";
	}
	
	@PostMapping(value = "/login")
	public String doLogin(@ModelAttribute MemberDTO params, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("로그인");
		String userid = request.getParameter("id");
		String userpw = request.getParameter("pw");
		
		MemberDTO member = memberService.login(userid, userpw);
		System.out.println(member);
		
		if(member == null) {
			
			String test = "failID";
			System.out.println("존재하지 않는 ID입니다.");
			model.addAttribute("noID", test);
			System.out.println(member);
			
			return "login/login";
		}
		
		
		//MemberDTO login = memberService.login(params, session);
		System.out.println("1111");
		boolean pwCheck = passwordEncoder.matches(params.getPw(), member.getPw());
		
		System.out.println("2222");
		if(member != null && pwCheck) { 
			session.setAttribute(SessionConstants.loginMember, member);
			System.out.println("loginS");
			
			return "redirect:/"; 
		} else {
			session.setAttribute(SessionConstants.loginMember, member);
			System.out.println("loginF");
			String test = "failPW";
			System.out.println("비밀번호가 맞지 않습니다.");
			model.addAttribute("failPW", test);
			
			return "login/login";
		}

		//HttpSession session = request.getSession();
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    
	    if (session != null) {
	        session.invalidate();   // 세션 날림
	    }
	    
	    return "redirect:/";
	}

	@GetMapping(value = "/register")
	public String openRegister(@RequestParam(value = "userNum", required = false) Long userNum, Model model) {
		
		if (userNum == null) {
			model.addAttribute("member", new MemberDTO());
		} else {
			MemberDTO member = memberService.getMemberDetail(userNum);
			if(member == null) {
				return "redirect:/login";
			}
			model.addAttribute("member",member);
		}
		return "login/register";
	}
	
	@PostMapping(value = "/register")
	public String registerBoard(final MemberDTO params) {
		
		try {
			boolean isRegistered = memberService.registerMember(params);
			if (isRegistered == false) {
				System.out.println(isRegistered);
				// TODO => 회원 등록에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/login";
	}
	
	@ResponseBody
	@PostMapping(value = "/idOverlapCheck")
	public int idOverlapCheck(@RequestParam("id") String id) {
		int result=0;
		
		try {
	    //    logger.info("idOverlapCheck 진입");
	    //    logger.info("전달받은 id:"+id);
			result = memberService.idOverlapCheck(id); // 중복확인한 값을 int로 받음
	    //    logger.info("확인 결과:"+result);
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
