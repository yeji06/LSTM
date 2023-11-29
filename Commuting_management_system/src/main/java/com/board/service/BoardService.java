package com.board.service;

import java.util.List;

import com.board.domain.AndroidBoardDTO;
import com.board.domain.AndroidCommentDTO;
import com.board.domain.BoardDTO;
import com.board.domain.CommentDTO;

public interface BoardService {
	public List<BoardDTO> getBoardList2(BoardDTO board);
	public BoardDTO selectBoardDetail2(Long idx);
	public boolean insertBoard(BoardDTO params);
	public boolean updateBoard2(BoardDTO updateBoard);
	public boolean registerBoard(BoardDTO params);

	public boolean deleteBoard(Long idx);

	//안드로이드
	public List<AndroidBoardDTO> getBoardList(AndroidBoardDTO board);
	public AndroidBoardDTO selectBoardDetail(Long idx);
	public List<AndroidBoardDTO> getCommentsForPost(Long postIdx);
	public boolean updateBoard(AndroidBoardDTO updateBoard);
}
