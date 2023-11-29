package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.mapper.EmployeeMapper;
import com.board.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private final UserMapper um;
	@Autowired
	private final BCryptPasswordEncoder BcryptpwEncoder;

//	로그인
	@Override
	public boolean loginUser(final UserDTO params) {
	    UserDTO user =um.selectUser(params);
	    if (user != null && user.getNickname().equals(params.getNickname()) && BcryptpwEncoder.matches(params.getPw(), user.getPw())) {
	    	return true; // 로그인 성공
	    } else {
	    	return false; // 로그인 실패
	    }
	}
	@Override
	public String getRole(final UserDTO params) {
	    UserDTO user =um.selectUser(params);
		return em.getRole((long)user.getEmployeeNo());
	}
	
// 회원가입	
	@Override
	public boolean insertUser(final UserDTO params) {
		int queryResult = 0;
		String encodePw = BcryptpwEncoder.encode(params.getPw());
		params.setPw(encodePw);
		
		queryResult = um.insertUser(params);
		return (queryResult == 1) ? true : false;
	}
	@Override
	public boolean sameNickname(final UserDTO params) { 
		UserDTO user = um.selectNickname(params);
		if (user == null) {return false;}
		return true;
	}
	@Autowired
	private EmployeeMapper em;
	@Override
	public boolean getEmpNo(final EmployeeDTO empNo) { //직원번호체크
		EmployeeDTO queryResult = em.selectEmployeeNo(empNo);
		return (queryResult == null) ? true : false;
	}
	@Override
	public boolean getEmpNo2(final UserDTO params) { //회원가입된 직원인지 확인
		int queryResult = um.selectEmployeeNo2(params);
		return (queryResult > 0) ? true : false;
	}
	@Override
	public int getEmpNo3(String nickname) {
		return um.selectEmpNo3(nickname);
	}
	@Override
	public String getNickname(EmployeeDTO employeeDTO) {
		return um.getNickname(employeeDTO);
	}
	@Override
	public boolean setPw(UserDTO params) {
		int queryResult = 0;
		String encodePw = BcryptpwEncoder.encode(params.getPw());
		params.setPw(encodePw);
		
		queryResult = um.setPw(params);
		return (queryResult == 1) ? true : false;
	}
}