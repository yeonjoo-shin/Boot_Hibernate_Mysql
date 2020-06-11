package com.iu.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	
	//@Test
	void memberLoginTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("johnny");
		memberVO.setPw("johnny");
		memberVO = memberRepository.findByIdAndPw(memberVO.getId(),memberVO.getPw());
		System.out.println(memberVO.getMemberFileVO().getFileName());
		System.out.println(memberVO.getMemberFileVO().getOriName());
		assertNotNull(memberVO);
	}
	
	
	
	//@Test
	void idCheck() { //중복확인
		MemberVO memberVO = new MemberVO();
		//boolean check = memberRepository.exists(memberVO);
		
		//System.out.println(check + " : check");
	}
	
	@Test
	public void insert2() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("mm");
		memberVO.setPw("mmmm");
		memberVO.setName("mmmm");
		memberVO.setEmail("mmmm@naver.com");
		memberVO.setPhone("01028272222");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		memberRepository.save(memberVO);
	}
	
	
	//@Test
	void insertTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu");
		memberVO.setPw("iu22");
		memberVO.setName("iu");
		memberVO.setEmail("iu@naver.com");
		memberVO.setPhone("01022222222");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");
		
		memberVO.setMemberFileVO(memberFileVO);

		
		memberRepository.save(memberVO);
		
		//member insert 성공
		//
		
//		List<MemberVO> ar = new ArrayList<>();
//		ar.add(memberVO);
//		ar.add(memberVO2);
//		
//		ar = memberRepository.saveAll(ar);
//		
//		assertNotNull(ar);
		
	}
	
	
	//@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu"); //수정할 id
		memberVO.setEmail("iu@gmail.com");//이메일 내용 수정
		memberVO.setPw("iu22");
		/*
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("change file name");
		memberFileVO.setOriName("change ori name");
		memberFileVO.setFileNum(1); //primary key를 줘야함. save에서 insert/update중에서 뭔지 몰라
		//바뀐애들 서로 넣어주기
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);*/
		
		memberVO=memberRepository.save(memberVO);
		
		assertNotNull(memberVO);
	}
	
	//@Test
	void deleteTest() {
		memberRepository.deleteById("johnny");
		
		/*
		//2명만 지우기
		MemberVO memberVO = new MemberVO();
		//memberVO.setId("jay");
		memberVO.setId("haechan");
		
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setId("dy");
		
		List<MemberVO> ar = new ArrayList<MemberVO>();
		ar.add(memberVO);
		ar.add(memberVO2);
		
		//memberRepository.delete(memberVO);
		memberRepository.deleteAll(ar);*/
	}

}
