package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yum.domain.CartDTO;


@Mapper
public interface CartMapper {

	// 카트 추가
	public int insertCart(CartDTO params) throws Exception;
	
	// 카트 수량 수정
	public int updateCartQty(CartDTO params);
	
//	// 카트 수량 수정
//	public int updateCartQty(CartDTO params);
	
	// 카트 삭제
	public int deleteCart(CartDTO params);
	
	// 카트 목록 
	public List<CartDTO> selectCartList(
			@Param("userNum") Long userNum
			, @Param("branchNum") Long branchNum);
	
	// 카트 목록 갯수 확인
	public Long countTotalCart(
			@Param("userNum") Long userNum
			, @Param("branchNum") Long branchNum);
	
	// 카트 확인 
	public CartDTO checkCart(CartDTO params);
	
	
	// 카트 수량 수정
	public int updateCart(CartDTO cartdto);
	
	public int addCart(CartDTO cartDTO);
	
	public CartDTO confirmCart(CartDTO cartDTO);
	
	public int updateCart2(CartDTO cartDTO);
	
	public List<CartDTO> getCartList(int userNum);
	
	public int deleteCart2(CartDTO cartDTO);
	
	public int updatePriceCart2(CartDTO cartDTO);
}
