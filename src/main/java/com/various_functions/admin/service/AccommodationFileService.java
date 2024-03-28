package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.admin.mapper.AccommodationsFileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccommodationFileService {

	private final AccommodationsFileMapper accommodationsFileMapper;
	
	// 다중파일 업로드
	@Transactional
	public void saveFile(final Long accommodationId, final List<AccommodationsFileDto> files) {
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		
		for(AccommodationsFileDto file: files) {
			file.setAccommodationId(accommodationId);
		}
		accommodationsFileMapper.accommodationFileSaveAll(files);
	}
	
	// 파일저장
	@Transactional
	public void saveFile(final Long accommodationId, final AccommodationsFileDto file) {
		if(file == null) {
			return;
		}
		file.setAccommodationId(accommodationId);
		accommodationsFileMapper.accommodationSave(file);
	}
	
	
}
