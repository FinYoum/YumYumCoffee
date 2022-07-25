package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

	// 장바구니 번호 추가
	private int cartNum;
	
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
	
	
	// 금액
	private int price;
	
	
	private String option1;
	
	private String option2;	
	
	private String option3;
	
	private String option4;
	

	
	/** 지점명 */
	private String branchName;

}