package com.board.domain;

import lombok.Data;

@Data
public class UserDTO{
	private Long employeeNo;
	private String nickname;
	private String pw;
	private String pw2; //confirm
	
	private String role;
}
