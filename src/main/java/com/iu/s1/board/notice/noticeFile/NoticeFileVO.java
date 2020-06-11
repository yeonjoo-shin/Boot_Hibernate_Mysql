package com.iu.s1.board.notice.noticeFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.iu.s1.board.notice.NoticeVO;

import lombok.Data;

@Data
@Entity
@Table(name = "noticeFile")
public class NoticeFileVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNum;
	
	//private long num;
		
	@Column
	private String fileName;
	@Column
	private String oriName;
	
	@ManyToOne
	@JoinColumn(name = "num")//num으로 연결
	private NoticeVO noticeVO;//누구랑 뭘로 연결할꺼냐 

}
