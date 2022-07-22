package com.yum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yum.constant.SessionConstants;
import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.service.MypageService;


@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
//	마이페이지 회원정보 불러오기
	@GetMapping(value="/mypage")
	public String openMypage(Model model, HttpSession session) {
		MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);
		model.addAttribute("member",member);
		CouponDTO params = new CouponDTO();
		params.setUserNum(member.getUserNum());
		int couponTotalCount = mypageService.countCoupon(params);
		model.addAttribute("couponTotalCount",couponTotalCount);
		return "mypage/mypage";
	}
		
//	마이페이지 >> 과거 주문 내역 페이지
	@GetMapping(value="/orderhistory")
	public String orderhistory(Model model, HttpSession session) {
		MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);
		model.addAttribute("member", member);		
		return "mypage/orderHistory";
	}
	
//	마이페이지 >> 내 정보 수정
	@GetMapping(value="/updateuser")
	public String updateuser(Model model, HttpSession session) {
		MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);;
		model.addAttribute("member", member);		
		return "mypage/updateUser";
	}
	
}
