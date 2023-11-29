package com.board.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AndroidCommentDTO extends CommonDTO {

  private Long no;

  private Long commentId;

  private Long boardIdx;

  private String commentNickname;

  private String content;
  
  private String commentRegDate; //안드로이드
  
}