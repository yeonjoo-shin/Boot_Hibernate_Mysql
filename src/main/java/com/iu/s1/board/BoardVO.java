package com.iu.s1.board;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor//생성자 만들기
@AllArgsConstructor//매개변수가 있는 생성자 만들기
@MappedSuperclass //얘는 테이블을 만드는게 아니라 다른애들의 부모역할을 할 것이다.
public class BoardVO { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증가 AI
	private long num;
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String contents;
	@Column
	@CreationTimestamp //현재 날짜를 넣어주기 위한 어노테이션 
	private Date regDate;
	@Column
	private long hit;
	

}
