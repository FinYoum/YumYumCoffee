package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

	// 회원번호
	private Long userNum;
	// 제품번호 
	private Long productNum;
	// 지점번호
	private Long branchNum;
	// 커스텀여부
	private Long shotCustom;
	
	// 개수
	private Long qty;
	// 총 금액
	private Long totalPrice;
	// 장바구니 추가 시간
	LocalDateTime insertTime;
	
	// 제품명
	private String name;

}