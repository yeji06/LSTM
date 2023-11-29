package com.board.service;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;

public interface UserService {
	public boolean insertUser(UserDTO params);
	public boolean loginUser(UserDTO params);
	public boolean sameNickname(UserDTO params);
	
	public boolean getEmpNo(EmployeeDTO empNo); //직원번호 확인
	public boolean getEmpNo2(UserDTO params); //이미 가입된 직원 확인
	public int getEmpNo3(String nickname);
	
	public String getNickname(EmployeeDTO employeeDTO);
	public boolean setPw(UserDTO params);
	
	public String getRole(UserDTO params);
	
}
