package com.various_functions.admin.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		if (CollectionUtils.isEmpty(files)) {
			return;
		}

		for (AccommodationsFileDto file : files) {
			file.setAccommodationId(accommodationId);
		}
		accommodationsFileMapper.accommodationFileSaveAll(files);
	}

	// 공지사항 id를 기반으로 파일정보 조회 id에 해당하는 파일 정보를 데이터베이스에서 가져오는 리스트로 변환
	public List<AccommodationsFileVo> findFileByAccommodationId(Long accommodationId) {
		return accommodationsFileMapper.findFileByAccommodationId(accommodationId);
	}
	
	// 파일 리스트 조회
	public List<AccommodationsFileVo> findAllFileByIds(final List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return accommodationsFileMapper.findAllByIds(ids);
	}

	/*
	 * 파일상세정보 조회
	 */
	public AccommodationsFileVo findFileById(final Long afId) {
		return accommodationsFileMapper.findById(afId);
	}

}
