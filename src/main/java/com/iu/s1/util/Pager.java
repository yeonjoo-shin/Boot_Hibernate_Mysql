package com.iu.s1.util;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Pager {
	
	private Integer page;//현재 페이지 몇번째 페이지를 볼 것인가
	private Integer size; // 페이지당 글 갯수
	
	private Integer startRow; //1
	private Integer lastRow; // 10
	
	private Integer totalPage; //총 페이지 갯수
	private Integer totalBlock; // 총 블록 갯수
	private Integer curBlock; //현재 블록
	
	private Integer startNum;//시작 번호
	private Integer lastNum;//끝 번호
	
	private String kind;
	private String search;
	
	
	//-------startrow/lastrow 계산하기
	public void makeRow() {
		this.startRow = this.getPage()-1;
		this.lastRow = this.getPage()*this.getSize();
	}
	
	//-----totalcount/totalpage 계산
	public void makePage(int totalPage) {
		//1. totalCount : 글 전체의 갯수
		
		
		//2. totalcount로 totalPage 계산
		/*this.totalPage= totalCount/this.getSize();
		if(totalCount%this.getCurPage() !=0) {
			this.totalPage++;
		}*/   	
		
		//totalPage를 매개변수로 받으면서 totalpage가 이미 계산을 다해서 가지고 오므로 계산을 할 필요가 없다.
		this.setTotalPage(totalPage); //or this.totalPage = totalPage;
		
		
		
		
		
		
		//3. totalpage ->totalblock 계산
		int perBlock=5;//블록당 page 갯수
		
		this.totalBlock = totalPage/perBlock;
		if(totalPage%perBlock !=0) {
			this.totalBlock++;
		}
		
		//4. curpage - curblock 찾기
		this.curBlock = this.page/perBlock;
		if(this.page%perBlock !=0) {
			this.curBlock++;
		}
		//5. curblock startnum, lastnum계산
		this.startNum = (this.curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		if(this.curBlock==this.totalBlock) { //5개씩 끊어줄거지만  글 갯수에 맞게하기
			this.lastNum=this.totalPage;
		}
	}
	
	
	public String getKind() {
	
		if(this.kind==null || this.kind.equals("")) {
			this.kind="title";
		}
		return kind;
	}

	
	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return search;
	}

	
	
	//getcurPage
	public Integer getPage() { //기본값 정해주기
		if(this.page ==null || this.page ==0) {
			this.page=1;
		}
		return page;
	}
	//getSize
	public Integer getSize() {
		if(this.size ==null || this.size ==0) {
			this.size=10;
		}
		return size;
	}
	
	

	
	
	
	

}
