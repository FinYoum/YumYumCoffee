package com.yum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.service.MypageService;


@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
//	마이페이지 회원정보 불러오기
	@GetMapping(value="mypage.do")
	public String openMypage(Model model) {
		int userNum=1; //회원번호
		MemberDTO user = mypageService.getUserDetail(userNum);
		model.addAttribute("user",user);
		
		CouponDTO params = new CouponDTO();
		params.setUserNum(userNum);
		int couponTotalCount = mypageService.countCoupon(params);
		model.addAttribute("couponTotalCount",couponTotalCount);
		return "yumyum/mypage";
	}
		
//	홈페이지
	@GetMapping(value="")
	public String openHome(Model model) {
		return "yumyum/index";
	}
	
//	마이페이지 >> 과거 주문 내역 페이지
	@GetMapping(value="orderhistory.do")
	public String orderhistory(@RequestParam(value = "userNum", required = true) int userNum
								, Model model) {
		MemberDTO user = mypageService.getUserDetail(userNum);
		model.addAttribute("user", user);		
		return "yumyum/orderHistory";
	}
}
