package com.various_functions.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
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
