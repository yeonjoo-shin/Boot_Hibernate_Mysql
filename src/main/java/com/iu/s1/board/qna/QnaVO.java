package com.iu.s1.board.qna;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iu.s1.board.BoardVO;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data // setter/getter 만들기
@EqualsAndHashCode(callSuper = false) //data랑 세트 있으면 더 좋다
@Table(name = "qna")
@Entity
public class QnaVO extends BoardVO {
	
	@Column //기본값 0을 설정해줌
	private long ref; //Long 타입이랑 기본값이 0이 아니라 null이들어가는 문제가 발생
	@Column
	private long step;
	@Column
	private long depth;
	
	
	@OneToMany(mappedBy = "qnaVO", cascade = CascadeType.ALL,fetch = FetchType.LAZY) //상대방이 알아야하는 자신의 이름(mappedBy)
	private List<QnaFileVO> boadFiles; //file은 여러개니까 List타입으로 선언
}
