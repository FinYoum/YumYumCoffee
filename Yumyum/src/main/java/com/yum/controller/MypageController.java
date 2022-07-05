package com.yum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yum.domain.CouponDTO;
import com.yum.domain.UserDTO;
import com.yum.service.MypageService;

//@RestController
@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
//	마이페이지 회원정보 불러오기
	@GetMapping(value="/yumyum/mypage.do")
	public String openMypage(Model model) {
		int userNum=2; //회원번호
		UserDTO user = mypageService.getUserDetail(userNum);
		model.addAttribute("user",user);
		
		CouponDTO params = new CouponDTO();
		params.setUserNum(userNum);
		int couponTotalCount = mypageService.countCoupon(params);
		model.addAttribute("couponTotalCount",couponTotalCount);
		return "/yumyum/mypage";
	}
	
	@GetMapping(value="")
	public String openHome(Model model) {
		return "yumyum/index";
	}
	
	@GetMapping(value="yumyum/orderhistory.do")
	public String orderhistory(@RequestParam(value = "userNum", required = true) int userNum, Model model) {
		UserDTO user = mypageService.getUserDetail(userNum);
		model.addAttribute("user", user);		
		return "yumyum/orderHistory";
	}
}
