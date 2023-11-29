package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.AndroidCommentDTO;
import com.board.domain.CommentDTO;

@Mapper
public interface CommentMapper {
   public List<CommentDTO> selectCommentList2(Long idx);
   public int selectCommentTotalCount(Long idx);
   public CommentDTO selectCommentDetail(Long commentId);
  
   public int insertComment(CommentDTO params);
   public int updateComment(CommentDTO params);
   public int deleteComment(Long commentId);
  
   //안드로이드
   public int insertComment(AndroidCommentDTO comment);
   public int AndroidDeleteComment(AndroidCommentDTO deleteCommentDTO);
   public List<AndroidCommentDTO> selectCommentList(Long idx);
}

