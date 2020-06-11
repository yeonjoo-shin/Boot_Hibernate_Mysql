package com.iu.s1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="member") //db에 create table member의 역할을 대신함

//@DynamicUpdate //데이터가 있는 것만 업데이트하고 나머지는 이전 데이터를 사용하겠다.
public class MemberVO {
	@NotEmpty
	@Id //primary key
	private String id;
	
	@NotEmpty
	@Size(max = 10,min = 4)
	@Column //일반 컬럼명
	private String pw;
	
	@Transient //테이블에서 제외
	private String pwCheck;
	
	@Column
	private String name;
	
	@Column
	@Email
	private String email;
	
	@Column
	private String phone;
	
	@Range(max = 200,min = 0)
	@Column
	private int age;
	
	//mapperBy ="join하는 Entity에 선언된 자기 자신의 Entity 변수명 "
	@OneToOne(mappedBy ="memberVO",cascade = CascadeType.ALL)
	private MemberFileVO memberFileVO;
	

}
