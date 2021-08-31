package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;  //auto_increment
	
	@Column(nullable=false, length = 100)
	private String title;
	
	@Lob  //대용량 데이터 
	private String content; //섬머노트 라이브러리 
	
	
	@ColumnDefault("0")
	private int count ;// 조회수 
	
	@ManyToOne(fetch = FetchType.EAGER) //Many =Board,  User = One
	//한 명의 유저는 여러개의 게시글을 쓸 수 있다.
	//여러 개의 게시글은 한 명의 유저에 의해 쓰여질 수 있	
	@JoinColumn(name="userId")
	private User userId; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다
	
	@OneToMany(mappedBy="board")
	//mappedBy 연관관계의 주인이 아니다. FK가 아니다. DB에 컬럼은 만들지 마라.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
