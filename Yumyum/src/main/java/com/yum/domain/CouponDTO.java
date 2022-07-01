package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponDTO {
//	쿠폰번호
	int cpNum;
//	회원번호
	int userNum;
//	결제번호, 사용후 업데이트
	int pmNum;
//	발급일
	LocalDateTime insertDate;
//	만료일
	LocalDateTime expirationDate;
//	쿠폰 종류
	String kind;
//	사용여부
	String deleteYn;
//	설명
	String description;
		
}
