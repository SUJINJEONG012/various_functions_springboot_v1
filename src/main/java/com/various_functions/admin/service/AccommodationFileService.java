package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.admin.mapper.AccommodationsFileMapper;
import com.various_functions.admin.vo.AccommodationsFileVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccommodationFileService {

	private final AccommodationsFileMapper accommodationsFileMapper;
	
	// 다중파일 업로드
	@Transactional
	public void saveFiles(final Long accommodationId, final List<AccommodationsFileDto> files) {
		log.info("파일저장하는 메서드!");
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		
		for(AccommodationsFileDto file: files) {
			file.setAccommodationId(accommodationId);
		}
		accommodationsFileMapper.accommodationFileSaveAll(files);
	}
	
	// 파일저장
	public List<AccommodationsFileVo> findFilesByAccommodationId(Long accommodationId) {	
		return accommodationsFileMapper.findFileByAccommodationId(accommodationId);
	}
	
	
}
