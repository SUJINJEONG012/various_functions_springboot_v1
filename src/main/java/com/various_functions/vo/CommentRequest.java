package com.various_functions.vo;

import java.util.List;

import com.various_functions.admin.vo.RoomInfoVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//댓글요청 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

	private Long id;
	private Long postId;
	private String content;
	private String writer;
	
}
