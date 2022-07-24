package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yum.domain.CartDTO;


@Mapper
public interface CartMapper {

	// 제품 추가
	public int insertCart(CartDTO params) throws Exception;
	
	// 제품 수량 수정
	public int updateCartQty(CartDTO params);
	
	// 제품이 장바구니에 있는지 확인
	public int countCartQty(CartDTO params);
	
	// 제품 삭제
	public int deleteCart(CartDTO params);
	
	// 장바구니 목록 
	public List<CartDTO> selectCartList(
			@Param("userNum") Long userNum
			, @Param("branchNum") Long branchNum);
	
	// 선택한 지점에 대한 장바구니 확인
	public int countTotalCart(
			@Param("userNum") Long userNum
			, @Param("branchNum") Long branchNum);
}
