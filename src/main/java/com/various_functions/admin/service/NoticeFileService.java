package com.various_functions.admin.service;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.mapper.NoticeFileMapper;
import com.various_functions.admin.vo.NoticeFileVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeFileService {

	private final NoticeFileMapper noticeFileMapper;
	
	// 파일저장
	@Transactional
	public void saveFiles(final Long noticeId, final List<NoticeFileDto> files) {
		log.info("saveFile : 파일저장하는 메서드 진입 !");
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		for (NoticeFileDto file : files) {
			file.setNoticeId(noticeId);
		}
		noticeFileMapper.saveAll(files);
		log.info("mapper에 저장된 파일 : {} ", files);
	}
	
	

	/*
	 * 공지사항 id를 기반으로 파일정보 조회 공지사항 ID에 해당하는 파일 정보를 데이터베이스에서 가져와서 리스트로 반환
	 * 게시글 번호(noticeId)를 기준으로 게시글에 등록된 모든 첨부파일을 조회
	 */
	public List<NoticeFileVo> findFilesByNoticeId(Long noticeId) {
		return noticeFileMapper.findFilesByNoticeId(noticeId);
	}
	

	/**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     * 리스트 타입의 파일 번호(ids)를 기준으로 여러 개의 첨부파일을 조회합니다. 
     * 이 메서드는 물리적 파일의 삭제 처리에 사용되며, 용도는 뒤에서 설명드리도록 하겠습니다.
     */
    public List<NoticeFileVo> findAllFileByIds(final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return noticeFileMapper.findAllByIds(ids);
    }
    
    
    // 파일로드
    public ResponseEntity<File> loadFile(String fileName) {
        // 해당 파일을 로드하고, Resource로 변환하여 ResponseEntity에 담아 반환합니다.
    	File file = new File("files/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file);
    }
 
	/* 파일삭제
	 * 리스트에 있는 각 파일 ID를 순회하며 개별적으로 삭제 작업을 수행
	 * 
	 */
    public void deleteFiles(List<Long> fileIds) {
        if (fileIds != null && !fileIds.isEmpty()) {
            for (Long fileId : fileIds) {
                noticeFileMapper.deleteById(fileId);
            }
        }
    }
	
	
	/**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    public NoticeFileVo findFileById(final Long fileId) {
        return noticeFileMapper.findById(fileId);
    }
	
	

}
