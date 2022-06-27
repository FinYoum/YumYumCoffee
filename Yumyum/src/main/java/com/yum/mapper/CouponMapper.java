package com.yum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.CouponDTO;

@Mapper
public interface CouponMapper {
	public int insertStampCoupon(CouponDTO params);

}
