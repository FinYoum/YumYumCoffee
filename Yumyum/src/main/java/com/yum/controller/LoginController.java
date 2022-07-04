package com.yum.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yum.domain.MemberDTO;
import com.yum.mapper.RegisterMapper;
import com.yum.service.MemberService;

@Controller
/* @RequestMapping("/yum") */
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private SqlSession sqlSession;
	
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
	@GetMapping(value = "/login")
	public String doLogin(@RequestParam(value = "userNum", required = false) Long userNum, Model model) {
		if (userNum == null) {
			model.addAttribute("member", new MemberDTO());
		} else {
			MemberDTO member = memberService.getMemberDetail(userNum);
			if(member == null) {
				return "redirect:/login";
			}
			model.addAttribute("member",member);
		}
		return "login/login";
	}
	
	@PostMapping(value = "/register")
	public String registerBoard(final MemberDTO params) {
		try {
			boolean isRegistered = memberService.registerMember(params);
			if (isRegistered == false) {
				System.out.println(isRegistered);
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
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
