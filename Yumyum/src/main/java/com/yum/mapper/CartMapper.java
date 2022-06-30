package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.CartDTO;


@Mapper
public interface CartMapper {

	// 카트 추가
	public int insertCart(CartDTO cartdto) throws Exception;
	// 카트 수량 수정
	public int updateCart(CartDTO cartdto);
	// 카트 삭제
	public int deleteCart (int cart_num);
	// 카트 목록 (전체 카트 목록(을 가져올 필요가 없지 현재만 보여줄 거니까))
	public List<CartDTO> getCart (int user_num);
	// 카트 확인 (현재 카트. 두 개 나눌 이유가 없지) 
	public CartDTO checkCart(CartDTO cartdto);
}
