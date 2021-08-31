package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> 자바(또는 다른 언어) object ->모두 테이블로 매핑해주는 기술

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //insert시에 nul인 필드를 제외시켜줌 
@Entity  // User 클래스가 MySQl에 자동으로 테이블이 생성됨 
public class User {

	@Id  //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 
	private int id;  //auto_increment 넘버링 전략!
	
	@Column(nullable = false,  length =30)	
	private String username; // 아이디
	
	@Column(nullable = false,  length =100)	 //해쉬로ㅗ 비밀번호 암호화 할 예
	private String password;
	
	@Column(nullable = false,  length =50)	
	private String email;
	
	//DB는 RoleType이라는게 없다. 그래서 아래와 같이 어노테이션 써줌 
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. ADMIN , USER
	
	@CreationTimestamp //시간이 자동 입력됨 
	private Timestamp createDate;
}
