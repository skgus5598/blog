package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


// DAO   -->JSP로 치면 Data Access Object
// 자동으로 bean등록이 된다
// @Repository 생략 가능하 
public interface UserRepository  extends JpaRepository<User, Integer>{

}
