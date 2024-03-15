package com.various_functions.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsFileDto {

	private Long afId;
	private Long aid;
	private String originalName;
	private String saveName;
	private int size;
	
	/* 
	 * 파일은 게시글이 생성(insert)된 후에 처리되어야 한다.
	 * 해당 메서드는 생성된 게시글 ID를 파일 요청 객체의 postId에 저장하는 용도로 사용
	*/
	public void setAid(Long aid) {
		this.aid = aid;
	}
	
}
