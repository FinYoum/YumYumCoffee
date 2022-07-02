package com.yum.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yum.adapter.GsonLocalDateTimeAdapter;
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
	

}
