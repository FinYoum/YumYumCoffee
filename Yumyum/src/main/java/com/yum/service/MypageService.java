package com.yum.service;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderDTO;

public interface MypageService {
//	내정보 수정
//	public boolean Info(SignInDTO params);//회원정보 테이블
//	회원탈퇴
//	과거 주문내역
	public OrderDTO getOrderDetail(int num, int date);
	public CouponDTO getCouponDetail(int num);
}
