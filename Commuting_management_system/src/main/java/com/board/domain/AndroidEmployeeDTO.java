package com.board.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AndroidEmployeeDTO extends CommonDTO {
	private int   idx;
	private String depName;
	private String jikup;
	private String nickname;
	private String pw;
	
	private Long  no;
	private String name;
	private String juminNum;
	private String telNum;
	private String email;
	private Long depNo;

} 