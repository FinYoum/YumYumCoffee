package com.yum.service;

import java.util.List;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderDTO;
import com.yum.domain.UserDTO;

public interface MypageService {
//	내정보 수정
//	public boolean Info(SignInDTO params);//회원정보 테이블
//	회원탈퇴
//	과거 주문내역
	public OrderDTO getOrderDetail(int num, int date);
	public List<CouponDTO> getCouponList(CouponDTO params);
	public UserDTO getUserDetail(int userNum);
	public int countCoupon(CouponDTO params);
	
}
