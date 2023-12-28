package com.various_functions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.dto.FileDto;
import com.various_functions.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;
	
	@Transactional
	public void saveFiles(final Long postId, final List<FileDto> files) {
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		for(FileDto file : files) {
			file.setPostId(postId);
		}
		
		fileMapper.saveAll(files);
	}
}
