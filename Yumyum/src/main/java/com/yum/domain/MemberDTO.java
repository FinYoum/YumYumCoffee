package com.yum.domain;

import lombok.Getter;
import lombok.Setter;

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
	String DeleteYn;
	// String stamp;
	
}
