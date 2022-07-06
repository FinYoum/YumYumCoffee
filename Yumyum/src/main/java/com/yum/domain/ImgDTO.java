package com.yum.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgDTO {

	/** 파일 번호 (PK) */
	private Long imgNum;

	/** 게시글 번호 (FK) */
	private Long productNum;

	/** 원본 파일명 */
	private String name;

	/** 파일 크기 */
	private long size;

}

