package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AndroidWorkDTO extends CommonDTO {
	private int num;
	private int no;
	private String name;
	private String jumin;
	private String workDate;
	private String workOn;
	private String workOff;
	private String workTime;
	private String selectedDate;
	private String send;
	
}
