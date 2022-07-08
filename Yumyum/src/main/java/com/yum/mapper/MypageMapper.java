package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderHistoryDTO;
import com.yum.domain.UserDTO;

@Mapper
public interface MypageMapper {
	public int insertCoupon();
	public List<CouponDTO> selectCouponList(CouponDTO params); //회원번호
	public UserDTO selectUserDetail(int userNum);
	public int countCoupon(CouponDTO Params);
	public List<OrderHistoryDTO> selectOrderHistory(
				@Param("userNum") int userNum
				, @Param("period") int period
				, @Param("firstIndex") int firstIndex
				);
	public int countOrder(
				@Param("userNum") int userNum
				, @Param("period") int period
			);

	
}
