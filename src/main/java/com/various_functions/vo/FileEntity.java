package com.various_functions.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.various_functions.admin.vo.RoomInfoVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

	private Long id;
	private Long postId;
	private String originalName;
	private String saveName;
	private long size;
	private Boolean deleteYn;
	private LocalDateTime createdDate;
	private LocalDateTime deletedDate;
}
