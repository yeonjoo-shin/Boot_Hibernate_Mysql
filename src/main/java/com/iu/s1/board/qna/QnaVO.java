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
	
	@Column
	private Long ref;
	@Column
	private Long step;
	@Column
	private Long depth;
	
	
	@OneToMany(mappedBy = "qnaVO", cascade = CascadeType.ALL,fetch = FetchType.LAZY) //상대방이 알아야하는 자신의 이름(mappedBy)
	private List<QnaFileVO> qnaFileVOs; //file은 여러개니까 List타입으로 선언
}
