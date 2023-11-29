package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.AndroidEmployeeDTO;
import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;

@Mapper
public interface UserMapper {

	public UserDTO test();

	public int insertUser(UserDTO user);

	public UserDTO login(UserDTO user);

	public UserDTO getUserInfo(String userNickname);

	public UserDTO overlaping(UserDTO user);

	public UserDTO selectUser(UserDTO params);
	
	public UserDTO checkNickname(UserDTO params);

	public AndroidEmployeeDTO checkNameJumin(AndroidEmployeeDTO employee);
	
	public UserDTO getUserIdByEmployeeNo(UserDTO params);

	public int updatePassword(UserDTO user);

 /*웹*/
  public UserDTO selectNickname(UserDTO params);
  public int selectEmployeeNo2(UserDTO params); // 회원가입시 중복확인
  public int selectEmpNo3(String nickname); // 닉네임 입력시 직원번호를 가져옴
  public UserDTO myInfo(UserDTO params);
  public String getNickname(EmployeeDTO employeeDTO);
  public int setPw(UserDTO params);


}
