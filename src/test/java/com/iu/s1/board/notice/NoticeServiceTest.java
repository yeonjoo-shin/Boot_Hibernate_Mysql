package com.iu.s1.board.notice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class NoticeServiceTest {
	
	@Autowired
	private NoticeService noticeService;
	
	//@Test
	/*
	void boardListTest() throws Exception{
		Pageable pageable = PageRequest.of(0, 10,Sort.Direction.DESC,"num");
		List<NoticeVO> ar = noticeService.getSelectList(pageable);
		for(NoticeVO noticeVO : ar) {
			System.out.println("num : " + noticeVO.getNum());
			System.out.println("title : " + noticeVO.getTitle());
		}
	}*/
}
