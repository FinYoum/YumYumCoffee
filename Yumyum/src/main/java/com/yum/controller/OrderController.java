package com.yum.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yum.constant.SessionConstants;
import com.yum.domain.CartDTO;
import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.domain.PaymentDTO;
import com.yum.service.CartService;
import com.yum.service.MypageService;
import com.yum.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OrderController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private CartService cartService;

//	장바구니에서 선택한 제품/총 금액/총 개수(=param), 회원의 쿠폰 정보를 가져와 화면에 띄우기	
	@PostMapping(value = { "/order" })
	public String openOrderList(@RequestParam Map<String, Object> param, HttpSession session,
			HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException {

		MemberDTO member = (MemberDTO) session.getAttribute(SessionConstants.loginMember);
		model.addAttribute("member", member);

		// 장바구니에서 체크한 제품 정보, 총 금액, 총 개수
		log.info("cartList : {}", param.toString());

		Long totalQty = Long.valueOf(param.get("totalQty").toString());
		// 결제 총금액 : totalSum > totalPrice
		Long totalPrice = Long.valueOf(param.get("totalSum").toString());

		// Object to List<CartDTO>
		ObjectMapper objectMapper = new ObjectMapper();
		List<CartDTO> cartList = objectMapper.readValue(param.get("cartList").toString(),
				new TypeReference<List<CartDTO>>() {
				});

		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("totalQty", totalQty);
		model.addAttribute("cartList", cartList);

		// 회원이 선택한 지점의 지점명
		String branchName = session.getAttribute("branchName").toString();
		model.addAttribute("branchName", branchName);

		// 로그인한 세션에서 UserNum을 받아와 해당하는 유저가 보유한 쿠폰 저장
		CouponDTO params = new CouponDTO();
		params.setUserNum(member.getUserNum());
		List<CouponDTO> couponList = mypageService.getCouponList(params);
		model.addAttribute("couponList", couponList);
		return "order/orderPage2";
	}

	// 결제 완료, 트랜젝션 처리
	@PostMapping(value = { "/orderconfirm" })
	public String orderDone(
			@RequestParam Map<String, Object> param
			, HttpSession session
			, HttpServletRequest request
			, Model model
			, PaymentDTO payment) throws JsonMappingException, JsonProcessingException {
		
		MemberDTO member = (MemberDTO) session.getAttribute(SessionConstants.loginMember);
		model.addAttribute("member", member);
		
		// 회원번호
		Long userNum = Long.valueOf(member.getUserNum());
		
		// 장바구니에서 체크한 제품 정보, 총 갯수, 총 금액 픽업시간
		Long totalQty = Long.valueOf(param.get("totalQty").toString()); 
		Long totalPrice = Long.valueOf(param.get("totalPrice").toString()); 
		String pickupYn = param.get("pickupYn").toString();
		
		// 장바구니 선택한 제품 목록
		ObjectMapper objectMapper = new ObjectMapper();
		List<CartDTO> cartList = objectMapper.readValue(param.get("cartList").toString(),
			new TypeReference<List<CartDTO>>() {});
		 
		 // 회원이 선택한 지점의 지점번호 
		Long branchNum = Long.valueOf(session.getAttribute("branchNum").toString());
		
		// 주문 내역 DB 추가
		payment.setUserNum(userNum);
		payment.setBranchNum(branchNum);
		payment.setPickupYn(pickupYn);
		payment.setTotalPrice(totalPrice);
		
		try {
			boolean resultOrder= paymentService.insertOrder(payment);
			log.debug("주문 내역 인서트 완료");
			if (resultOrder=true) {
				for(CartDTO cart:cartList) {
					boolean resultOrderDetail = paymentService.insertOrderDetail(cart);
					log.debug("주문 세부 내역 인서트 완료");
					if(resultOrderDetail=true) {
						cartService.deleteCart(cart);
						log.debug("장바구니 삭제 완료");
					}
				}
				boolean resultCoupon = paymentService.insertCoupon(userNum, totalQty);
				log.debug("쿠폰 적립 완료");
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		 
		return "order/orderconfirm";
	}

}
