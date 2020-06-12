package com.iu.s1.board.notice;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.iu.s1.board.BoardVO;
import com.iu.s1.board.notice.noticeFile.NoticeFileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "notice")
@Entity
@DynamicUpdate(value = true)
public class NoticeVO extends BoardVO{
	
	//one은 자기 자신, many는 밑에 선언한  file이 여러개 
	@OneToMany(mappedBy = "noticeVO",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private  List<NoticeFileVO> boadFiles;
	
	//FetchType :  eager : notice 조회할 때 noticeFile도 같이 조회
	//			: lazy : notice조회 할때  오직 notice 만 조회하고, 쓰려고 선언해야지만 noticeFile을 조회할 수 있다. 
	

}
