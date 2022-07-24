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
		List<OrderDTO> orderList = paymentService.selectOrder(orderNum);
		String branchName= paymentService.getBranchName(orderNum);
		
		MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);
		CouponDTO params = new CouponDTO();
		params.setUserNum(member.getUserNum());
		System.out.println("userNum : "+params);
		List<CouponDTO> couponList = mypageService.getCouponList(params);

		
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
		
		model.addAttribute("couponList", couponList);
		model.addAttribute("orderList", orderList);
		model.addAttribute("branchName", branchName);

		return "order/orderPage2";
	}
	
}
