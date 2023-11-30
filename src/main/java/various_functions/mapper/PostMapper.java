package various_functions.mapper;

import org.apache.ibatis.annotations.Mapper;

import various_functions.domain.PostRequest;

@Mapper
public interface PostMapper {

	/*게시글 저장
	 * @Param params - 게시글 정보
	 * */
	void save(PostRequest params);
	
	
}
