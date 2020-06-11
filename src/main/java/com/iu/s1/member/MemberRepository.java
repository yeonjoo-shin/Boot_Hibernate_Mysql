package com.iu.s1.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO, String> {//primarykey의 타입
	//내장메서드
	//save(insert)
	//쿼리문 대신 메서드가 제공
	// 메서드를 토해서 쿼리문이 자동으로 생성
	//기본 제공 메서드
	// S save(T)	: insert,update
	//< S extends T> S save(Interable<? extends T>) : 다중 insert,update
	// void deleteById(ID) : PK를 통한 삭제
	// void delete(T)	: 주어진 Entity 삭제 
	// void deleteAll(Interable<? extends T>): 주어진 모든 Entity delete
	// void deleteAll() : 모든  Entity delete
	// List<T> findAll() : 모든 Entity 조회
	// Optional<T(MemberVO)> findById(ID(primary key)) : PK로 단일 Entity select
	//long count : Entity 모든 갯수
	//boolean existsById(ID) : PK로 Entity 존재여부
	// 사용자가 생성하는 메서드 - 쿼리메서드
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence
	
	//public abstract ....
	//select * from member where id=? and pw=?
	public MemberVO findByIdAndPw(String id, String pw);
	
	public MemberVO findByid(String id);
	
	//delete
	public void deleteById(String id);
	
	
	
}
