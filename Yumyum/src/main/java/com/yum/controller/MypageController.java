package com.yum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yum.constant.SessionConstants;
import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.service.MypageService;


@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@ResponseBody
	@PostMapping(value="/identification")
	public int identification(@ModelAttribute MemberDTO params,
			@SessionAttribute(name = SessionConstants.loginMember, required = false) MemberDTO loginMember) {
		
		MemberDTO member = mypageService.identification(params.getPw(),loginMember.getId());
		int result=0;
		
		try {
	        logger.info("identification 진입");
	        logger.info("전달받은 pw:"+member.getPw());
	        logger.info("matches pw:"+params.getPw());
			boolean pwCheck = passwordEncoder.matches(params.getPw(), member.getPw());
	        logger.info("pwCheck:"+pwCheck);
			
	        logger.info("세션에서 가져오는 id:"+loginMember.getId());
	        //result = mypageService.identification(pw,loginMember.getId());

			if(member != null && pwCheck) { 
				result = 1;
				// 22일 해야할 것: 내정보수정 페이지 이동 되었으니 
				// 페이지 띄우고 정보 업데이트 할 수있게 하고
				// 회원탈퇴까지 하기!!!
		        logger.info("확인 결과:"+result);
			} else {
				result = 0;
		        logger.info("확인 결과:"+result);
			}
	        
		} catch (Exception e) {
			result=0;
			System.out.println(e.getMessage());
		}
		return result;
	}
	
//	마이페이지 회원정보 불러오기
	@GetMapping(value="/mypage")
	public String openMypage(Model model,
			@SessionAttribute(name = SessionConstants.loginMember, required = false) MemberDTO loginMember) {
		
		int userNum=loginMember.getUserNum(); //회원번호
		MemberDTO member = mypageService.getUserDetail(userNum);
		model.addAttribute("member",member);
		
		CouponDTO params = new CouponDTO();
		params.setUserNum(userNum);
		int couponTotalCount = mypageService.countCoupon(params);
		model.addAttribute("couponTotalCount",couponTotalCount);
		
		return "mypage/mypage";
	}
		
//	마이페이지 >> 과거 주문 내역 페이지
	@GetMapping(value="/orderhistory")
	public String orderhistory(@RequestParam(value = "userNum", required = true) int userNum
								, Model model) {
		MemberDTO member = mypageService.getUserDetail(userNum);
		model.addAttribute("member", member);		
		
		return "mypage/orderHistory";
	}
	
//	마이페이지 >> 내 정보 수정
	@GetMapping(value="/updateuser")
	public String updateuser(@RequestParam(value = "userNum", required = true) int userNum
								, Model model) {
		MemberDTO member = mypageService.getUserDetail(userNum);
		model.addAttribute("member", member);		
		
		return "mypage/updateUser";
	}
	
}
