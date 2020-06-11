package com.iu.s1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

//getter/setter
@Data
@Table(name="memberFile")
@Entity
public class MemberFileVO {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)//테이블 자동증가 처리 AI
	private long fileNum;

	@Column
	private String fileName;//하드 디스크에 저장되는 이름
	@Column
	private String oriName;
	
	@OneToOne
	@JoinColumn(name = "id")
	private MemberVO memberVO; //memberfilevo의 mapped 이름과ㅏ 같아야함
}
