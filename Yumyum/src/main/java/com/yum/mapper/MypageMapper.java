package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderDTO;
import com.yum.domain.UserDTO;

@Mapper
public interface MypageMapper {
	public OrderDTO selectOrderDetail(int userNum,int date);
	public int selectTest();
	public int insertCoupon();
	public List<CouponDTO> selectCouponList(CouponDTO params); //회원번호
	public UserDTO selectUserDetail(int userNum);
	public int countCoupon(CouponDTO Params);
	
	
}
