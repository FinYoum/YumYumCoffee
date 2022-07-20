package com.yum.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yum.SessionConstants;
import com.yum.domain.MemberDTO;
import com.yum.service.EmailService;
//import com.yum.service.EmailService;
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
	
	@GetMapping(value = "/home")
	public String home(@SessionAttribute(name = SessionConstants.loginMember, required = false)MemberDTO loginMember,
						HttpServletRequest request, Model model) {
		//sessionCall(request, model);
		
		model.addAttribute("member", loginMember);
		System.out.println(request);
		System.out.println(model);
		
		return "yumyum/index";
	}
	
	@GetMapping(value = "/login")
	public String getLogin() {
		
		return "login/login";
	}
	
	@PostMapping(value = "/login")
	public String doLogin(@ModelAttribute MemberDTO params, Model model, HttpSession session, 
							HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("로그인222");
		MemberDTO member = memberService.login(params.getId(), params.getPw());
		System.out.println(member);
		
		if(member == null) {
			
			String failID = "failID";
			model.addAttribute("noID", failID);
			System.out.println("noID");
			
			return "login/login";
		}
		
		//MemberDTO login = memberService.login(params, session);
		boolean pwCheck = passwordEncoder.matches(params.getPw(), member.getPw());
		if(member != null && pwCheck) { 
			
			session.setAttribute(SessionConstants.loginMember, member);
			model.addAttribute("member", member);
			System.out.println("loginS");
			
			return "redirect:/home"; 
			
		} else {
			
			System.out.println("loginF");
			String failPW = "failPW";
			model.addAttribute("failPW", failPW);
			
			return "login/login";
		}
	}
	
	@PostMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    
	    if (session != null) {
	        session.invalidate();   // 세션 날림
	    }

		return "redirect:/home"; 
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

	@GetMapping(value = "/findId")
	public String pageFindId() {
		
		return "login/findId";
	}
	@ResponseBody
	@PostMapping(value = "/findId")
	public String findId(@RequestParam("name") String name, @RequestParam("email") String email) {
		
		String result = "";
			try {
				result = memberService.findId(name, email); // 확인한 값을 String로 받음
				//model.addAttribute("userID", params.getId());
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result = "";
			}
		
		return result;
	}
	

	@GetMapping(value = "/findPw")
	public String pageFindPw() {
		
		return "login/findPW";
	}
	@ResponseBody
	@PostMapping(value = "/findPw")
	public int findPw(@RequestParam("id") String id, @RequestParam("email") String email) {
		
		int result = 0;
		
			try {
				result = memberService.findPw(id, email); // 확인한 값을 int로 받음
				//model.addAttribute("userID", params.getId());
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result = 0;
			}
		
		return result;
	}
	
}
