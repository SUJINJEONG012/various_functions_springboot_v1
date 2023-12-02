package various_functions;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import various_functions.domain.PostRequest;
import various_functions.domain.PostResponse;
import various_functions.mapper.PostMapper;

@SpringBootTest
public class PostMapperTest {

	@Autowired
	private PostMapper postMapper;
	
	@Test
	 void save() {
		PostRequest params = new PostRequest();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("안젤라");
		params.setNoticeYn(false);
		postMapper.save(params);
		
		List<PostResponse> posts = postMapper.findAll();
		System.out.println("전체 게시글 개수는 : " + posts.size() + "개 입니다.");
	}
}

