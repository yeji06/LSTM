package com.board.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommentDTO extends AndroidCommentDTO {
	// 웹
	private LocalDateTime commentRegDate2; 
}