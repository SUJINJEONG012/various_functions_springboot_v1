package various_functions.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
//게시판에 글을 작성할 때 입력하는 필드는 제목,내용,작성자,공지글 여부
	private Long id;
	private String title;
	private String content;
	private String writer;
	private boolean noticeYn;
}
