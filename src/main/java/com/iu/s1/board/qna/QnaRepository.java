package com.iu.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	@Query("select q from QnaVO q where q.num=:num")
	QnaVO qnaSelect(Long num);
	
	//전체조회가 아닌경우
	@Query("select q.title,q.writer from QnaVO q where q.num=:num")
	Object[] qnaSelect2(Long num);
	
	
	//update
	@Modifying
	@Transactional
	@Query(value = "update QnaVO q set q.title=?1,q.contents=?2 where q.num=?3")// 숫자는 인덱스 번호
	void qnaUpdate(String title,String contents,Long num); //순서를 맞워야함
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update qna q set q.title=:title,q.contents=:contents where q.num=:num")// nativeQuery 원래 쓰던대로 쓰기허락...?but 사용X
	void qnaUpdate2(String title,String contents,Long num); //순서는 상관없지만 매개변수명이 위에 :명과 같아야한다.
	
	
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
