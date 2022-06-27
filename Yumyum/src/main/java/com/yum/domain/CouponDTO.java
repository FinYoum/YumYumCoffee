package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class CouponDTO {
	/** 쿠폰번호 (PK) */
	private int cpNum;
	
//	회원번호
	private int userNum;
	
//	결제번호
	private int pmNum;
	
//	발급일
	private LocalDateTime insertDate;
	
//	만료일
	private LocalDateTime expirationDate;
	
//	종류
	private String kind;
	
//	사용여부
	private String deleteYn;
	
//	쿠폰 설명
	private String description;


}
