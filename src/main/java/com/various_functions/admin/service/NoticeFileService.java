package com.various_functions.admin.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.mapper.NoticeFileMapper;
import com.various_functions.admin.vo.NoticeFileVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeFileService {

	private final NoticeFileMapper noticeFileMapper;

	// 파일저장
	@Transactional
	public void saveFile(final Long noticeId, final List<NoticeFileDto> files) {
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		for (NoticeFileDto file : files) {
			file.setNoticeId(noticeId);
		}
		noticeFileMapper.saveAll(files);
	}



	/*
	 * 공지사항 id를 기반으로 파일정보 조회 공지사항 ID에 해당하는 파일 정보를 데이터베이스에서 가져와서 리스트로 반환
	 */
	public List<NoticeFileVo> findFilesByNoticeId(Long noticeId) {
		return noticeFileMapper.findFilesByNoticeId(noticeId);
	}

	/**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    public List<NoticeFileVo> findAllFileByIds(final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return noticeFileMapper.findAllByIds(ids);
    }
	
	/*
	 * 파일 삭제 
	 * 
	 * */
	public void deleteAllFileByIds(final List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
            return;
        }
	}
	

}
