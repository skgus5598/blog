package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

//사용자가 요청 ->응답(Data)
@RestController
public class HttpConstrollerTest {
	
	private static final String TAG="HttpConstrollerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : " + m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG +"setter : " + m.getUsername());
		return "lombok test 완료";
	}
	

	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다 
	//http://localhost:8080/http/get
	@GetMapping("/http/get")  //(select)
	public String getTest(Member m) {		
		return "get 요청 : " ;
	}
	
	//http://localhost:8080/http/post
	@PostMapping("/http/post")   //(insert)
	public String postTest(Member m) {
		return "post 요청:  ";
	}
	
	//http://localhost:8080/http/put
	@PutMapping("/http/put")   //(update)
	public String putTest() {
		return "put 요청 ";
	}
	
	//http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")   //(delete)
	public String deleteTest() {
		return "delete 요청 ";
	}
}
