package com.board.service;

import java.util.List;

import com.board.domain.AndroidCommentDTO;
import com.board.domain.CommentDTO;

public interface CommentService {
  public boolean registerComment(CommentDTO params);

  public boolean deleteComment(Long commentId);

  public List<CommentDTO> getCommentList2(Long idx);
  
  public boolean AndroidDeleteComment(AndroidCommentDTO deleteCommentDTO);
  public List<AndroidCommentDTO> getCommentList(Long idx);

}

