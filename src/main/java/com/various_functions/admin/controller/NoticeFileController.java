package com.various_functions.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.service.NoticeFileService;
import com.various_functions.admin.vo.NoticeFileVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoticeFileController {
	
	private final String uploadPath = "/Users/jeongsujin/upload/"; // 파일이 저장된 경로
	//private final String uploadPath = "C:\\Users\\NCIN\\single-upload-files"; // 파일이 저장된 경로

    private final NoticeFileService noticeFileService;
	private final FileUtils fileUtils;

	/*파일리스트 조회 => 공지사항에 첨부된 파일목록을 가져오는 RESTful API로 구현  
	 * 이 API를 호출하여 프론드엔드에서 파일 목록을 가져올 수 있다. 
	 */
	@GetMapping("/admin/notice/{noticeId}/files")
	public List<NoticeFileVo> findAllAdminFileByNoticeId(@PathVariable final Long noticeId){
	    log.info("파일컨트롤러 메서드 진입 !!!");
	    return noticeFileService.findFilesByNoticeId(noticeId);
	}

	@GetMapping("/notice/{noticeId}/files")
	public List<NoticeFileVo> findAllUserFileByNoticeId(@PathVariable final Long noticeId){
	    log.info("파일컨트롤러 메서드 진입 !!!");
	    return noticeFileService.findFilesByNoticeId(noticeId);
	}
	
	
	//파일수정
    @GetMapping("/admin/notice/{noticeId}/files/{fileId}/update")
    public ResponseEntity<Resource> AdminUpdateFile(@PathVariable final Long noticeId, @PathVariable final Long fileId) {
        
    	NoticeFileVo file = noticeFileService.findFileById(fileId);

        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이미지 파일인 경우
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }
	
	// 첨부파일 다운로드
    @GetMapping("/admin/notice/{noticeId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable final Long noticeId, @PathVariable final Long fileId) {
        NoticeFileVo file = noticeFileService.findFileById(fileId);
       
        //fileUtils에서 가져오는걸 넣음
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }
    
    //파일보기
    @GetMapping("/admin/notice/{noticeId}/files/{fileId}/view")
    public ResponseEntity<Resource> AdminviewFile(@PathVariable final Long noticeId, @PathVariable final Long fileId) {
        
    	NoticeFileVo file = noticeFileService.findFileById(fileId);
        
    	//fileUtils에서 가져오는걸 넣음
        Resource resource = fileUtils.readFileAsResource(file);
        
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이미지 파일인 경우
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }
    
   //파일보기
    @GetMapping("/notice/{noticeId}/files/{fileId}/view")
    public ResponseEntity<Resource> UserviewFile(@PathVariable final Long noticeId, @PathVariable final Long fileId) {
        
    	NoticeFileVo file = noticeFileService.findFileById(fileId);

        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이미지 파일인 경우
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }
    
   

}
