package com.yum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yum.domain.CartDTO;
import com.yum.domain.MemberDTO;
import com.yum.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/cart/add")
	@ResponseBody
	public String insertCart(CartDTO cartdto, HttpServletRequest request) {
		// 로그인 체크
//		HttpSession session = request.getSession();
//		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
//		if(memberDTO == null) {
//			return "5";
//		} // 인터셉터 했는데 로그인 체크 왜 해야해
		
		
		// 카트 등록
		
		int add = cartService.insertCart(cartdto);
				
		return add + "";
	};
}
