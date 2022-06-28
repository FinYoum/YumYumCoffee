package com.yum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yum.mapper.MypageMapper;
//import com.yum.domain.CouponDTO;
//import com.yum.mapper.CouponMapper;

@SpringBootTest
public class MapperTests {
	
	@Autowired
	private MypageMapper mypageMapper;
	
	@Test
	public void test() {
//		try {
		System.out.println(mypageMapper.selectTest());
//		} catch(RuntimeException e) {
//			System.out.println(e);
//		} catch(Exception e) {
//			System.out.println(e);
//		}
	}
	
	@Test
	public void test2() {
//		try {
		System.out.println(mypageMapper.insertCoupon());
//		} catch(RuntimeException e) {
//			System.out.println(e);
//		} catch(Exception e) {
//			System.out.println(e);
//		}
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
////		System.out.println("결과는 " + result + "입니다.");
//	}
	
}