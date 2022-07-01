package com.yum;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yum.domain.CouponDTO;
import com.yum.domain.UserDTO;
import com.yum.mapper.MypageMapper;
import com.yum.service.MypageService;
//import com.yum.domain.CouponDTO;
//import com.yum.mapper.CouponMapper;

@SpringBootTest
public class MapperTests {
	
	@Autowired
	private MypageMapper mypageMapper;

	
//	쿠폰 발급 테스트/성공
//	@Test
//	public void testOf() {
//		System.out.println(mypageMapper.insertCoupon());
//	}
//	
//	mypageMapper 쿠폰리스트 가져오기/성공
//	@Test
//	public void testOfSelectCouponDetail() {
//		CouponDTO params = new CouponDTO();
//		int userNum = 2;
//		params.setUserNum(userNum);
//		List<CouponDTO> couponList = mypageMapper.selectCouponList(params);
//		if (CollectionUtils.isEmpty(couponList) == false) {
//			for (CouponDTO coupon : couponList) {
//				System.out.println("=========================");
//				System.out.println(coupon.getKind());
//				System.out.println(coupon.getDescription());
//				System.out.println(coupon.getExpirationDate());
//				System.out.println("=========================");
//			}
//		}
//	}
	
	
//	 mapper쿠폰갯수 테스트/성공
//	 @Test
//	 public void testOfCountCouponMapper() {
//		int userNum = 2;
//		CouponDTO params=new CouponDTO();
//		params.setUserNum(userNum);
//		System.out.println(mypageMapper.countCoupon(params));
//	}
	

	
	
	
//	회원정보 불러오기/성공
//	@Test
//	public void testOfSelectDetail() {
//		int userNum=2;
//		UserDTO user = mypageMapper.selectUserDetail(userNum);
//		try {
//	        String userJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user);
//	
//			System.out.println("=========================");
//			System.out.println(userJson);
//			System.out.println("=========================");
//	
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//	}
}
//
//
//	@Test
//	public void testOfInsert() {
//		CouponDTO params = new CouponDTO();
//		params.setUserNum(50000); 
//		params.setPmNum(60000);
//		params.setKind("스탬프");
//		try {
//		int result = CouponMapper.insertStampCoupon(params);
//		System.out.println("결과는 " + result + "입니다.");
//		} catch(RuntimeException e) {
//			System.out.println(1);
//			System.out.println(e);
//		} catch(Exception e) {
//			System.out.println(2);
//			System.out.println(e);
//		}
//		System.out.println("결과는 " + result + "입니다.");
//	}
	