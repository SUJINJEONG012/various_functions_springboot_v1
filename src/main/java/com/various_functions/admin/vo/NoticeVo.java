package com.various_functions.admin.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.various_functions.admin.vo.RoomInfoVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* 게시글 응답 클래스 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVo {

	private Long noticeId; //pk
	private String title; //제목
	private String content; //내용
	private String writer; //작성자
	private int viewCnt; //조회수
	private Boolean noticeYn; //공지글 여부
	private Boolean deleteYn; //삭제여부
	private LocalDateTime createdDate; //생성일시
	private LocalDateTime modifiedDate; //최종 수정일시
	
	private List<NoticeFileVo> imageList;
}
