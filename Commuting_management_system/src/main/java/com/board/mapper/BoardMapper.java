package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.AndroidBoardDTO;
import com.board.domain.AndroidCommentDTO;
import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	public int insertBoard2(BoardDTO params);
	public int updateBoard2(BoardDTO params);
	public int selectBoardTotalCount2(BoardDTO params);

	public List<BoardDTO> selectBoardList2(BoardDTO params);
	public BoardDTO selectBoardDetail2(Long idx);

	public int deleteBoard(Long idx);
	public void view(Long idx);
		
	//안드로이드
	public int insertBoard(AndroidBoardDTO board);
	public int updateBoard(AndroidBoardDTO updateBoard);

	public List<AndroidBoardDTO> selectBoardList(AndroidBoardDTO params);
	public AndroidBoardDTO selectBoardDetail(Long idx);
	public int selectBoardTotalCount(AndroidBoardDTO board);
}

