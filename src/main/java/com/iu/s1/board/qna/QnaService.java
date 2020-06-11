package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QnaService {
	@Autowired
	private QnaRepository qnaRepository;
	
	public QnaVO boardWrite(QnaVO qnaVO) throws Exception{
		//ref 0인 상태
		qnaVO = qnaRepository.save(qnaVO);
		//insert
		qnaVO.setRef(qnaVO.getNum());
		//다시 업데이트
		return qnaRepository.save(qnaVO);
	}
	
	public Page<QnaVO> boardList(Pageable pageable) throws Exception{
		
		Page<QnaVO> page= qnaRepository.findAll(pageable);
		return page;
	}
}
