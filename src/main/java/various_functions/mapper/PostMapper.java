package various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import various_functions.domain.PostRequest;
import various_functions.domain.PostResponse;

@Mapper
public interface PostMapper {

	/*게시글 저장
	 * @Param params - 게시글 정보
	 * */
	void save(PostRequest params);
	
	/*
	 * 게시글 상세정보 조회
	 * */
	PostResponse findById(Long id);
	
	/*
	 * 게시글 수정
	 * */
	void update(PostRequest params);
	
	/*
	 * 게시글 삭제
	 * @Param id - PK
	 * */
	void deletedById(Long id);
	
	/* 게시글 리스트 조회 @return 게시글 리스트 */
	List<PostResponse> findAll();
	
	/*
	 * 게시글 수 카운팅
	 * */
	int count();
	
}
