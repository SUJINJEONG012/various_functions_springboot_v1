package com.various_functions.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.mapper.NoticeFileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeFileService {	

	private final NoticeFileMapper noticeFileMapper;
	
	// 다중파일 업로드
	@Transactional
	public void saveFile(final Long noticeId, final List<NoticeFileDto> files) {
		if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (NoticeFileDto file : files) {
            file.setNoticeId(noticeId);
        }
        noticeFileMapper.noticeFilesaveAll(files);
    }

	// 파일저장
	@Transactional
	public void saveFile(final Long noticeId, final NoticeFileDto file) {
		if(file == null) {
			return;
		}
		file.setNoticeId(noticeId);
		noticeFileMapper.noticeFileSave(file);
	}
	
	/*
	 * 공지사항 id를 기반으로 파일정보 조회
	 * 공지사항 ID에 해당하는 파일 정보를 데이터베이스에서 가져와서 리스트로 반환
	 * */ 
	public List<NoticeFileDto> findFilesByNoticeId(Long noticeId){
		return noticeFileMapper.findFilesByNoticeId(noticeId);
	}
	
	
	

}
