package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WorkDTO extends CommonDTO {
	private int num; //일련번호
	private int no;  //직원번호
	private String name;
	private String jumin;
	private LocalDateTime workDate;
	private LocalDateTime workOn;
	private LocalDateTime workOff;
	private String workTime;
}
