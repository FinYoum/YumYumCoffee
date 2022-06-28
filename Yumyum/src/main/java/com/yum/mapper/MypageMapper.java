package com.yum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderDTO;

@Mapper
public interface MypageMapper {
	public OrderDTO selectOrderDetail(int userNum,int date);
	public int selectTest();
	public int insertCoupon();
	public CouponDTO selectCouponDetail(int num);
	
	
}
