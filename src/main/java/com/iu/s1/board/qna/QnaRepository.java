package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	public List<QnaVO> findByRefAndStepGreaterThan(long ref,long step);
	
	
	public Page<QnaVO> findByTitleContaining(String search,Pageable pageable);
	public Page<QnaVO> findByWriterContaining(String search,Pageable pageable);
	public Page<QnaVO> findByContentsContaining(String search,Pageable pageable);
	/*
	//타이틀 검색
	Page<QnaVO> findByTitleContaining(String search,Pageable pageable);
	//contents 검색
	Page<QnaVO> findByContentsContaining(String search,Pageable pageable);
	// 작성자 검색
	Page<QnaVO> findByWriterContaining(String search,Pageable pageable);
	
	*/

}
