package com.yum.domain;

public class MemberDTO {
	String id;
	String name;
	// String pw;
	int tel;
	String email;
	String birth;
	int authority;
	String stamp;
	
	public MemberDTO() {
		super();
	}
	public MemberDTO(String id, String name, int tel, String email, String birth, int authority, String stamp) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.birth = birth;
		this.authority = authority;
		this.stamp = stamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
}
