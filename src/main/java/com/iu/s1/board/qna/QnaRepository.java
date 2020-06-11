package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	public Page<QnaVO> findByTitleContaining(String search,Pageable pageable,String kind);
	public Page<QnaVO> findByWriterContaining(String search,Pageable pageable,String kind);
	public Page<QnaVO> findByContentsContaining(String search,Pageable pageable,String kind);

}
