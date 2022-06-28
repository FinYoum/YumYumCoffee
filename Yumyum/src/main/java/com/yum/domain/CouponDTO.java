package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponDTO {
	int cpNum;
	int userNum;
	int pmNum;
	LocalDateTime insertDate;
	LocalDateTime expirationDate;
	String kind;
	String deleteYn;
	String description;
		
}
