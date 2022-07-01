package com.yum.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import com.yum.service.MypageService;

@RestController
public class CouponController {

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

	

}