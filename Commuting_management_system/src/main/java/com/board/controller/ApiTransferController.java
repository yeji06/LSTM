package com.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.AndroidEmployeeDTO;
import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;
import com.board.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiTransferController {
	@Autowired
	UserMapper um;
	@Autowired
	UserService us;
	@Autowired
	BCryptPasswordEncoder BcryptpwEncoder;

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO userData() {
		UserDTO user = um.test();
		return user;
	}

	@PostMapping("/save-user")
	@ResponseBody
	public ResponseEntity<String> save(@RequestBody UserDTO user) {
		log.info("employeeno={}", user.getEmployeeNo());
		log.info("nickname={}", user.getNickname());
		log.info("pw={}", user.getPw());

		// 시큐리티 비밀번호 암호화 저장
		String encodePw = BcryptpwEncoder.encode(user.getPw());
		System.out.println("BcryptpwEncoder:" + encodePw);
		user.setPw(encodePw);

		int result = um.insertUser(user);

		if (result > 0) {
			log.info("사용자 정보가 성공적으로 저장되었습니다.");
			return ResponseEntity.ok("회원가입 성공");
		} else {
			log.error("사용자 정보 저장에 실패하였습니다.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
		}
	}

	@PostMapping("/AndroidLogin")
	@ResponseBody
	public UserDTO login(@RequestBody UserDTO user) {
	    log.info("nickname={}", user.getNickname());
	    log.info("pw={}", user.getPw());
	    log.info("로그인 요청");
	    UserDTO userFromDatabase = um.login(user);

	    // 시큐리티 입력한 비밀번호와 데이터베이스에 있는 비밀번호 비교
	    boolean passwordMatches = BcryptpwEncoder.matches(user.getPw(), userFromDatabase.getPw());
	    
	    if (passwordMatches) {
	        log.info("비밀번호 일치");
	        UserDTO userWithInfo = um.getUserInfo(user.getNickname());

	        if (userWithInfo != null) {
	            // 사용자 정보 설정 (다른 필요한 정보도 설정 가능)
	            // 직원 번호 설정
	            return userWithInfo; // UserDTO 객체 반환 (직원 번호를 포함한 정보)
	        }
	    } else {
	        log.info("비밀번호 불일치");
	    }

	    return null; // 로그인 실패 시 null 반환
	}


	@PostMapping("/check")
	@ResponseBody
	public Map<String, Boolean> check(@RequestBody UserDTO user) {
	    log.info("nickname={}", user.getNickname());
	    UserDTO username = um.checkNickname(user);
	    Map<String, Boolean> response = new HashMap<>();
	    
	    if (username != null) {
	        log.info("아이디는 중복");
	        // 중복되는 닉네임이 있음을 알립니다.
	        response.put("isDuplicate", true);
	    } else {
	        log.info("중복이 아닙니다.");
	        // 중복이 없을 때 사용자 정보를 반환합니다.
	        response.put("isDuplicate", false);
	    }

	    return response;
	}

	@PostMapping("/checkname")
	@ResponseBody
	public AndroidEmployeeDTO check(@RequestBody AndroidEmployeeDTO employee) {
	    log.info("이름={}", employee.getName());
	    log.info("주민={}", employee.getJuminNum());
	    AndroidEmployeeDTO employeeinfo =um.checkNameJumin(employee);
	    if(employeeinfo !=null) {
	    	log.info("정보 찾기 성공");
	    	return employeeinfo;
	    }else {
	    	log.info("해당정보는 없습니다.");
	    	AndroidEmployeeDTO NewEmployee = new AndroidEmployeeDTO();
	    	return NewEmployee;
	    		}
	}
	
	@PostMapping("/UpdatePw")
	@ResponseBody
	public boolean update(@RequestBody UserDTO user) {
		log.info("UpdatePw={}",user.getPw());
		log.info("nickname={}",user.getNickname());
		
		String encodePw = BcryptpwEncoder.encode(user.getPw());
		System.out.println("BcryptpwEncoder:" + encodePw);
		user.setPw(encodePw);
		
		int chkNum = um.updatePassword(user);
		
		if(chkNum == 1) {
			return true;
		}else{
			return false;
		}
	}
	
	@PostMapping("/getNicknameByEmployeeNo")
	@ResponseBody
	public UserDTO getid(@RequestBody UserDTO user) {
		log.info("employeeno={}",user.getEmployeeNo());
		UserDTO id=um.getUserIdByEmployeeNo(user);
		
		return id;
	}
	

	
}
