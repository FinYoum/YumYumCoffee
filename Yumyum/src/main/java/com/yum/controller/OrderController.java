package com.yum.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.yum.constant.SessionConstants;
import com.yum.domain.CartDTO;
import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.domain.OrderDTO;
import com.yum.service.MypageService;
import com.yum.service.PaymentService;

import groovy.transform.Undefined.EXCEPTION;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
		model.addAttribute("member",member);
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
	
	
//	
//	 @RequestMapping(value = {"/order2"}, method = { RequestMethod.POST, RequestMethod.PATCH }    )
//	 @ResponseBody
//	public String openOrderList2( OrderDTO orderDTO,
//			   HttpSession session, Model model) throws EXCEPTION {      
//					
//				log.info("cartList : {}", orderDTO.toString());
//				String cartList=Arrays.toString(orderDTO.getCartList());;
//			
//				log.info("carts " +cartList.toString());	
//					
//				return "order/orderPage2";
//	 }
	
	
//	"cnt" :cnt,
	//"totalSum":totalSum,
	//"cartList" :JSON.stringify(totalArr)
//	@RequestParam(value="cartList[]") List <CartDTO> cartList, 
//	   @RequestParam int cnt, @RequestParam int totalPrice,
		//@ResponseBody
	   @RequestMapping(value = {"/order"}, method = { RequestMethod.POST, RequestMethod.PATCH }    )
		public String openOrderList( @RequestParam  Map<String, Object> param,
			   HttpSession session, Model model) throws JsonMappingException, JsonProcessingException {      
				log.info("cartList");
				//log.info("cartList : {}, {} , {}", cnt , totalSum , param.size());	
				log.info("cartList : {}", param.toString());
				
				String cnt=(String)param.get("cnt");
				String totalSum=(String)param.get("totalSum");
		
				ObjectMapper objectMapper=new ObjectMapper();
				List<CartDTO>  orderList=objectMapper.readValue(param.get("cartList").toString(), 
						new TypeReference<List<CartDTO>>(){});
				
				//Object object=(Object)param.get("cartList");
//				List<CartDTO> cartList=(List<CartDTO>)param.get("cartList");
				log.info("cartList : {}",cnt);
				log.info("cartList : {}",totalSum);
				log.info("cartList : {}",orderList.size());
				
				for(CartDTO cartDTO :  orderList ) {
					log.info("주문 목록 {}" , cartDTO.toString());
					//DB 저장
				}
				
				
	      //주문리스트와 이미지 테이블을 조인해서 모델에 저장
//	            model.addAttribute("cartList", cartList);
//
//	            //주문하는 지점명 저장
//	            model.addAttribute("branchName", "테스트 중");
//	            
//	            //로그인한 세션에서 UserNum을 받아와 해당하는 유저가 보유한 쿠폰 저장
//	            MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);
//	            model.addAttribute("member",member);
//	            CouponDTO params = new CouponDTO();
//	            params.setUserNum(member.getUserNum());
//	            System.out.println("userNum : "+params);
//	            List<CouponDTO> couponList = mypageService.getCouponList(params);
//	            model.addAttribute("couponList", couponList);
	            return "order/orderPage2";
	   }
	
}
