package com.yum.service;

import java.util.List;

import com.yum.domain.CouponDTO;
import com.yum.domain.MemberDTO;
import com.yum.domain.OrderHistoryDTO;

public interface MypageService {
//	내정보 수정
//	public boolean Info(SignInDTO params);//회원정보 테이블
//	회원탈퇴
//	과거 주문내역
	public List<CouponDTO> getCouponList(CouponDTO params);
	public MemberDTO getUserDetail(int userNum);
	public int countCoupon(CouponDTO params);
	public List<OrderHistoryDTO> getOrderHistory(int userNum, int period, int firstIndex);
	public int countOrder(int userNum, int period);
	
}
