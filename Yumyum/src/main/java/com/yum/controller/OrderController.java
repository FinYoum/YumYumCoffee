package com.yum.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yum.constant.SessionConstants;
import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.domain.OrderDTO;
import com.yum.service.MypageService;
import com.yum.service.PaymentService;

@Controller
public class OrderController {
	
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MypageService mypageService;

	@GetMapping(value = "/order/{orderNum}")
	public String openOrderList(HttpSession session, @PathVariable("orderNum") int orderNum, Model model) {
		
		//주문리스트와 이미지 테이블을 조인해서 모델에 저장
		List<OrderDTO> orderList = paymentService.selectOrder(orderNum);
		model.addAttribute("orderList", orderList);

		//주문하는 지점명 저장
		String branchName= paymentService.getBranchName(orderNum);
		model.addAttribute("branchName", branchName);
		
		//로그인한 세션에서 UserNum을 받아와 해당하는 유저가 보유한 쿠폰 저장
		MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);
		CouponDTO params = new CouponDTO();
		params.setUserNum(member.getUserNum());
		System.out.println("userNum : "+params);
		List<CouponDTO> couponList = mypageService.getCouponList(params);
		model.addAttribute("couponList", couponList);

		
		//		List<ImgDTO> productImg = Collections.emptyList();
//		List<Integer> pnum = new ArrayList<>();
//		for (int i = 0; i < orderList.size(); i++) {
//			System.out.println("orderList의 길이 : "+ orderList.size());
//			System.out.println("회차 : " + i);
//			int num = orderList.get(i).getProductNum();
//			System.out.println("num : " + num);
//			pnum.add(i, num);
//			System.out.println("pnum : " + pnum);
//		}
//		for(int j = 0; j < pnum.size(); j++) {
//			System.out.println("pnum이 받아오는 값 : " + pnum.get(j));
//			productImg = productService.getAttachFileList((long)pnum.get(j));
//		}
//
//		model.addAttribute("productImg", productImg);
		

		return "order/orderPage2";
	}
	
}
