package com.board.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AndroidBoardDTO extends CommonDTO {
	public Long		idx; // 쿼리문에 null값으로 입력되야함.
	public String 	nickname;
	public String	title;
	public String 	content;

	public int 		viewCnt;

	public String 	regDate;
	public String 	upDate; 

	public int comments;
}