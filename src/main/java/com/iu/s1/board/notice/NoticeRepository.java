package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
	//프리미티브 타입음 안되므로 long 안됨
	//select
	//findBy컬럼명조건
	
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	public int countByTitleContaining(String search);
	public int countByWriterContaining(String search);
	public int countByContentsContaining(String search);
	
	public List<NoticeVO> findByTitleContaining(String search,Pageable pageable);
	public List<NoticeVO> findByWriterContaining(String search,Pageable pageable);
	public List<NoticeVO> findByContentsContaining(String search,Pageable pageable);
	
	//select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);
	// 파라미터로 받아올 num 
	
	//select * from notice where num between 6 and 10
	public List<NoticeVO> findByNumBetween(long start,long last);
	
	//select * from where title like ?? order By num desc

	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);
	
}
