package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller = RestController 
@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	// {id} 주소로 파라미터를 전달받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이되잖아.
		//그럼 return null이 리턴되잖아.. 그럼 프로그램에 문제가 있지 않겠니?
		//Optional로 너의 User객체를 감싸서 가져올테니..
		//null인지 아닌지 판단해서 return해!!!
		
/*		람다식   훨씬 편하다고 함 (밑에 코드보다!)
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다 + " +id);
		}) ;
*/
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다 + " +id);
			}
		});
/*		
		요청 : 웹브라우저 
		user 객체 : 자바객체 
		 변환 (웹브라우저가 이해할 수 있는 데이터로) -->json으로! 
		 스프링부트 = MessageConverter라는 애가 응답 시에 자동 작동 
		 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		 user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
*/		return user;
	}
	
	
	
	// http://localhost:8000/blog/dummy/join(요청) 
	// http의 body에 username, password, email데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {
	public String join(User user) {
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("id : "  + user.getId());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
