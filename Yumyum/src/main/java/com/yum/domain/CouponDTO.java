package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CouponDTO {
	/** 쿠폰번호 (PK) */
	private int cp_num;
	
//	회원번호
	private int user_num;
	
//	결제번호
	private int pm_num;
	
//	발급일
	private LocalDateTime insert_date;
	
//	만료일
	private LocalDateTime expiration_date;
	
//	종류
	private String kind;
	
//	사용여부
	private String delete_yn;


}
