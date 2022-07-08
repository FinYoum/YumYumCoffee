package com.yum.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
	
	// 장바구니 번호 추가
	private int cartNum;
	// 제품번호 
	private int productNum;
	// 회원번호
	private int userNum;
	// 개수
	private int qty;
	// 금액
	private int price;
	// 커스텀여부_yn
	private int shotCustom;
	// 총 금액
	private int totalPrice;

}