package com.yum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yum.domain.CouponDTO;
import com.yum.mapper.CouponMapper;

public class MapperTests {
	@Autowired
	private CouponMapper CouponMapper;


	@Test
	public void testOfInsert() {
		CouponDTO params = new CouponDTO();
		params.setUserNum(50000); 
		params.setPmNum(60000);
		params.setKind("스탬프");
		try {
		int result = CouponMapper.insertStampCoupon(params);
		System.out.println("결과는 " + result + "입니다.");
		} catch(RuntimeException e) {
			System.out.println(1);
			System.out.println(e);
		} catch(Exception e) {
			System.out.println(2);
			System.out.println(e);
		}
//		System.out.println("결과는 " + result + "입니다.");
	}
	


}
