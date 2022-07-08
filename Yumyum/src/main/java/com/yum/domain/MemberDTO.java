package com.yum.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MemberDTO {
	
	int userNum;
	
	String id;
	
	String name;
	
	String pw;
	
	String tel;
	
	String email;
	
	String birth;
	
	int authority;
	
	String deleteYn;
	// String stamp;
	
}
