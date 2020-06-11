package com.iu.s1.util;

public class Pager {
	
	private Long curPage;//현재 페이지 몇번째 페이지를 볼 것인가
	private Integer perPage; // 페이지당 글 갯수
	
	private long startRow; //1
	private long lastRow; // 10
	
	private long totalPage; //총 페이지 갯수
	private long totalBlock; // 총 블록 갯수
	private long curBlock; //현재 블록
	
	private long startNum;//시작 번호
	private long lastNum;//끝 번호
	
	private String kind;
	private String search;
	
	
	//-------startrow/lastrow 계산하기
	public void makeRow() {
		this.startRow = (this.getCurPage()-1);
		this.lastRow = this.getCurPage()*this.getPerPage();
	}
	
	//-----totalcount/totalpage 계산
	public void makePage(long totalCount) {
		//1. totalCount : 글 전체의 갯수
		
		//2. totalcount로 totalPage 계산
		this.totalPage= totalCount/this.getPerPage();
		if(totalCount%this.getCurPage() !=0) {
			this.totalPage++;
		}
		
		//3. totalpage ->totalblock 계산
		long perBlock=5L;//블록당 page 갯수
		
		this.totalBlock = totalPage/perBlock;
		if(totalPage%perBlock !=0) {
			this.totalBlock++;
		}
		
		//4. curpage - curblock 찾기
		this.curBlock = this.curPage/perBlock;
		if(this.curPage%perBlock !=0) {
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
	
		if(this.kind==null) {
			this.kind="title";
		}
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
		
	}

	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
	public Long getCurPage() { //기본값 정해주기
		if(this.curPage ==null || this.curPage ==0) {
			this.curPage=1L;
		}
		return curPage;
	}
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	public Integer getPerPage() {
		if(this.perPage ==null || this.perPage ==0) {
			this.perPage=10;
		}
		return perPage;
	}
	
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public long getStartRow() {
		return startRow;
	}
	
	public long getLastRow() {
		return lastRow;
	}
	
	public long getTotalPage() {
		return totalPage;
	}
	
	public long getTotalBlock() {
		return totalBlock;
	}
	
	public long getCurBlock() {
		return curBlock;
	}
	
	public long getStartNum() {
		return startNum;
	}
	
	public long getLastNum() {
		return lastNum;
	}
	
	
	
	

}
