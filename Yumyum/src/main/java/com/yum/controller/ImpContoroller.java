package com.yum.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public class ImpContoroller {

	
	@Controller
	public class HomeController {
		
		private IamportClient api;
		
		public HomeController() {
	    	// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
			this.api = new IamportClient("3452420157053319","DXI0XVlgpUwS8B3Hj9cFxozTrGn6CXmfU4fS0B8pvFhDglkzvxe3VNXnukt7hPmcUC4UddkJSCn9XIJt");
		}
			
		@ResponseBody
		@RequestMapping(value="/verifyIamport/{imp_uid}")
		public IamportResponse<Payment> paymentByImpUid(
				Model model
				, Locale locale
				, HttpSession session
				, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
		{	
				return api.paymentByImpUid(imp_uid);
		}
		
	}
}
