package com.iu.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberJoinTest {
	
	@Autowired
	private MemberRepository memberRepository;

	//@Test
	void idCheck() {
		MemberVO memberVO = new MemberVO();
		
		///Example example = new Example;
		boolean check = memberRepository.existsById("mark");
		
		System.out.println("check : " + check);
	}

	@Test
		public void  insertTest() {
			MemberVO memberVO = new MemberVO();
			memberVO.setId("teil");
			memberVO.setPw("teil");
			memberVO.setName("teil");
			memberVO.setAge(24);
			memberVO.setEmail("teil@naver.com");
			memberVO.setPhone("01025987478");
			MemberVO memberVO2 = new MemberVO();
			memberVO2.setId("haechan");
			memberVO2.setPw("haechan");
			memberVO2.setName("haechan");
			memberVO2.setAge(28);
			memberVO2.setEmail("haechan@naver.com");
			memberVO2.setPhone("01012365498");
			
			List<MemberVO> ar = new ArrayList<MemberVO>();
			ar.add(memberVO);
			ar.add(memberVO2);
			
			ar=memberRepository.saveAll(ar);
			
			assertNotNull(memberVO);
		}
}
