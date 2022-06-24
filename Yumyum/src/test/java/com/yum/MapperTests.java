package com.yum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import com.yum.domain.BoardDTO;
import com.yum.domain.CouponDTO;
import com.yum.mapper.BoardMapper;
import com.yum.mapper.CouponMapper;

@SpringBootTest
class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	private CouponMapper couponMapper;

//	@Test
//	public void testOfInsert() {
//		BoardDTO params = new BoardDTO();
//		params.setTitle("1번 게시글 제목");
//		params.setContent("1번 게시글 내용");
//		params.setWriter("테스터");
//
//		int result = boardMapper.insertBoard(params);
//		System.out.println("결과는 " + result + "입니다.");
//	}
	
	@Test
	public void testOfInsertCoupon() {
		CouponDTO params = new CouponDTO();
		params.setCp_num(50000);
		params.setPm_num(60000);
		params.setKind("스탬프");

		int result = couponMapper.insertStampCoupon(params);
		System.out.println("결과는 " + result + "입니다.");
	}

}