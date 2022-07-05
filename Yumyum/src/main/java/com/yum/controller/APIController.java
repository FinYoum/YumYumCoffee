package com.yum.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.yum.domain.OrderHistoryDTO;
import com.yum.service.MypageService;

@RestController
public class APIController {

	@Autowired
	private MypageService mypageService;

	@GetMapping(value = "yumyum/coupon/{userNum}")
	public JsonObject getCommentList(@PathVariable("userNum") int userNum, @ModelAttribute("params") CouponDTO params) {
		
		JsonObject jsonObj = new JsonObject();

		List<CouponDTO> couponList = mypageService.getCouponList(params);
		if (CollectionUtils.isEmpty(couponList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(couponList).getAsJsonArray();
			jsonObj.add("couponList", jsonArr);
		}
		return jsonObj;
	}

	
	@GetMapping(value = "yumyum/orderhistory/{userNum}/{period}/{firstIndex}/{lastIndex}")
	public JsonObject getOrderHistoryList(
				@PathVariable("userNum") int userNum, 
				@PathVariable("period") int period,
				@PathVariable("firstIndex") int firstIndex,
				@PathVariable("lastIndex") int lastIndex
				) {
		System.out.println("TEST");
		System.out.println(firstIndex);
		System.out.println(lastIndex);
		
		JsonObject jsonObj = new JsonObject();
		List<OrderHistoryDTO> orderHistoryList = mypageService.getOrderHistory(userNum, period, firstIndex, lastIndex);
		if (CollectionUtils.isEmpty(orderHistoryList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(orderHistoryList).getAsJsonArray();
			jsonObj.add("orderHistoryList", jsonArr);
		}
		return jsonObj;
	}

}