package com.board.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BoardDTO extends AndroidBoardDTO {
	// 웹
	public LocalDateTime regDate2;
	public LocalDateTime upDate2; 
	
}