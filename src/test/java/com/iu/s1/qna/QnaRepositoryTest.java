package com.iu.s1.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.s1.board.qna.QnaRepository;
import com.iu.s1.board.qna.QnaService;
import com.iu.s1.board.qna.QnaVO;

@SpringBootTest
class QnaRepositoryTest {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@Autowired
	private QnaService qnaService;
	
	@Test
	public void insertTest() {
		QnaVO qnaVO = new QnaVO();
		
		qnaVO.setTitle("title1");
		qnaVO.setContents("contents1");
		qnaVO.setWriter("writer2");
		
		qnaVO = qnaService.boardWrite(qnaVO);
		assertNotNull(qnaVO);
	}

}
