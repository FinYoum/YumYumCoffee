package com.yum.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public class ImpContoroller {

	
	@RestController
	public class HomeController {
		
		private IamportClient api;
		
		public HomeController() {
	    	// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
			this.api = new IamportClient("7464134155225840","nxuuws9VZYJEhe0mU3qZozR473fqlO8L09YUHLXDKNtso1W1n1DzzxbQQR0iNaDMu6asnChOKceyVgiL");
		}
		
		@ResponseBody
		@RequestMapping(value="/payment/{imp_uid}")
		public IamportResponse<Payment> paymentByImpUid(
				Model model
				, Locale locale
				, HttpSession session
				, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
		{	
				return api.paymentByImpUid(imp_uid);
		}
			
		@ResponseBody
		@RequestMapping(value="/orderComplete", produces = "application/text; charset=utf8", method = RequestMethod.GET)
		public String orderComplete(
				@RequestParam(required = false) String imp_uid
				, @RequestParam(required = false) String merchant_uid
				, Model model
				, Locale locale
				, HttpSession session ) throws IamportResponseException, IOException
		{	
			IamportResponse<Payment> result = api.paymentByImpUid(imp_uid);
			
			// 결제 가격과 검증결과를 비교한다.
			if(result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
				System.out.println("검증통과");
			}
			
			return "payment";
		};
		
		
	}
	
}
