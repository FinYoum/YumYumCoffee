package com.yum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yum.service.MypageService;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	@GetMapping(value="/yumyum/mypage.do")
	public String openMypage(Model model) {
		return "yumyum/mypage";
	}
}
