package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.AndroidCommentDTO;
import com.board.domain.CommentDTO;
import com.board.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentMapper commentMapper;
	
	@Override
	public boolean AndroidDeleteComment(AndroidCommentDTO deleteCommentDTO) {
		// TODO Auto-generated method stub
		return false;
	}

@Override
public List<AndroidCommentDTO> getCommentList(Long idx) {
	List<AndroidCommentDTO> commentList = Collections.emptyList();
	
	int commentTotalCount = commentMapper.selectCommentTotalCount(idx);
	if (commentTotalCount > 0) {
		commentList = commentMapper.selectCommentList(idx);
	}
	
	return commentList;
}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	
  @Override
  public boolean registerComment(CommentDTO params) {
    int queryResult = 0;

    if (params.getCommentId() == null) {
      queryResult = commentMapper.insertComment(params);
    } else {
      queryResult = commentMapper.updateComment(params);
    }

    return (queryResult == 1) ? true : false;
  }

  @Override
  public boolean deleteComment(Long commentId) {
    int queryResult = 0;

    CommentDTO comment = commentMapper.selectCommentDetail(commentId);

    if (comment != null && "N".equals(comment.getDeleteYn())) {
      queryResult = commentMapper.deleteComment(commentId);
    }

    return (queryResult == 1) ? true : false;
  }


@Override
public List<CommentDTO> getCommentList2(Long idx) {
	List<CommentDTO> commentList = Collections.emptyList();

    int commentTotalCount = commentMapper.selectCommentTotalCount(idx);
    if (commentTotalCount > 0) {
      commentList = commentMapper.selectCommentList2(idx);
    }

    return commentList;
}

}