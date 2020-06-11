package com.iu.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.s1.board.notice.noticeFile.NoticeFileVO;

@SpringBootTest
class NoticeRepositoryTest {
	@Autowired
	private NoticeRepository noticeRepository;
	
	private NoticeVO noticeVO;
	private NoticeFileVO noticeFileVOs;
	
	@BeforeEach//실행전에 한번씩하자
	public void beforeEach() {
		noticeVO = new NoticeVO();
		noticeVO.setTitle("title");
		noticeVO.setWriter("writer");
		noticeVO.setContents("contents");
		List<NoticeFileVO> noticeFileVOs = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("filename5");
		noticeFileVO.setOriName("oriname5");
		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);
		
		noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("secondfileName5");
		noticeFileVO.setOriName("secondoriName5");
		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);
		
		noticeVO.setNoticeFileVOs(noticeFileVOs);
	}
	
	//@Test
	public void customTest() {
		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0L);
		for (NoticeVO noticeVO : ar) {
			System.out.println("num : " + noticeVO.getNum());
			
		}
	}
	
	//@Test
	public void customTest2() {
		List<NoticeVO> ar = noticeRepository.findByNumBetween(2,3);
		for(NoticeVO noticeVO : ar) {
			System.out.println("num2 : " +noticeVO.getNum());
		}
	}
	
	@Test
	public void customTest3() {
		List<NoticeVO> ar = noticeRepository.findByTitleContainingOrderByNumDesc("1");
		for(NoticeVO noticeVO :ar) {
			System.out.println("title : " +noticeVO.getTitle());
		}
	}
	
	
	//@Test
	public void insertTest() throws Exception{
		
		for(int i=0;i<100;i++) {
			noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setContents("contents"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO = noticeRepository.save(noticeVO);
		}
		
		
		assertNotNull(noticeVO);
	}
	
	//@Test
	public void updateTest() throws Exception{
		noticeVO.setNum(3);
		noticeVO.setTitle("update title3");
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
	}
	
	//@Test
	public void deleteTest() throws Exception{
		noticeRepository.deleteById(2L); //id가 long타입이므로 L 붙이기
		boolean check=noticeRepository.existsById(2L);//삭제했지만 잘 지워졌는지 조회하기
		assertEquals(false, check); //잘 지워졌으면 false
	}
	
	//selectList
	@Test
	public void selectListTest() throws Exception{
		List<NoticeVO> ar = noticeRepository.findAll();
		for(NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getTitle());
		}
		assertNotEquals(0, ar.size());//1개 이상 리스트에 출력하면 좋겟...
	}
	//selectOne
	//@Test
	public void selectOneTest() throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(4L);
		noticeVO = opt.get();//opt에서 get해서 꺼내야함
		//if(opt.isPresent())  //존재하냐
		
		System.out.println(noticeVO.getRegDate());
		assertNotNull(noticeVO);
	}

}
