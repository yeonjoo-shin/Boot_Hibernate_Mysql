package com.iu.s1.board.qna;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
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
	
	
	
	
	public QnaVO boardSelect(QnaVO qnaVO) throws Exception{
		qnaVO=qnaRepository.findById(qnaVO.getNum()).get();
		return qnaVO;
	}
	
	public QnaVO boardhit(QnaVO qnaVO) throws Exception{
		qnaVO=qnaRepository.findById(qnaVO.getNum()).get();
		qnaVO.setHit(qnaVO.getHit()+1);
		return qnaRepository.save(qnaVO);
	}
	
	
	public QnaVO boardReply(QnaVO qnaVO) throws Exception{
		//qnavo에는 부모의 num같은 정보만 들어감 부모정보
		QnaVO childVO = new QnaVO();//데이터 옮겨두기
		childVO.setTitle(qnaVO.getTitle());
		childVO.setWriter(qnaVO.getWriter());
		childVO.setContents(qnaVO.getContents());
		
		//update 
		//ref 부모의 ref랑 같은것
		//step이 부모의 step 보다 큰 것들
		//step을 1씩 증가
		
		//1. 부모의 정보를 조회
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(qnaVO.getRef(), qnaVO.getStep());
		//2. step을 +1
		for(QnaVO q : ar) {
			q.setStep(q.getStep()+1);
		}
		//3.이 정보를 전체적으로 update
		qnaRepository.saveAll(ar);
		
		//save
		//자기자신의 ref는 부모의 ref
		//자기자신의 step 은 부모의 step+1
		//자기자신의 depth 는 부모의 depth+1
		childVO.setRef(qnaVO.getRef());
		childVO.setStep(qnaVO.getStep()+1);
		childVO.setDepth(qnaVO.getDepth()+1);
		qnaRepository.save(childVO);
		
		return childVO;
	}
	
	
	public Page<QnaVO> boardList(Pager pager/*Pageable pageable,String search,String kind*/) throws Exception{
		/*
		Page<QnaVO> page= qnaRepository.findByTitleContaining(search, pageable);
		
		//kind.equals(null) // 애초에 kind가 올때 null.equals로 오기 때문에 null.equals=null이냐고 묻는 것이라서 애초에 null에러가 뜸
		if(kind == null) {
			page = qnaRepository.findAll(pageable);
		}else {
			if(kind.equals("title")) {
				 page=qnaRepository.findByTitleContaining(search, pageable);
			}else if(kind.equals("writer")){
				page = qnaRepository.findByWriterContaining(search, pageable);
			}else {
				page = qnaRepository.findByContentsContaining(search, pageable);
			}
		}*/
		
		pager.makeRow();//0번 부터 만들어줌
		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(),Sort.by("ref").descending().and(Sort.by("step").ascending()));
		Page<QnaVO> page = null; //전체페이지의 갯수가 이미 계산되어서 들어가있음
		
		if(pager.getKind().equals("title")) {
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		pager.makePage(page.getTotalPages());
		
		return page;
	}
}
