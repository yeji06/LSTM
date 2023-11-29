package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.AndroidBoardDTO;
import com.board.domain.AndroidCommentDTO;
import com.board.domain.BoardDTO;
import com.board.domain.CommentDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.PaginationInfo;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper bm;
	
	//안드로이드
	@Override
	public List<AndroidBoardDTO> getBoardList(AndroidBoardDTO board) {
		List<AndroidBoardDTO> boardList = Collections.emptyList();
		int boardTotalCount = bm.selectBoardTotalCount(board);
		if (boardTotalCount > 0) { boardList = bm.selectBoardList(board); }
		return boardList;
	}
	
	@Override
	public List<AndroidBoardDTO> getCommentsForPost(Long postIdx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean updateBoard(AndroidBoardDTO updateBoard) {
		// 게시물 업데이트 로직
		System.out.println("게시물 업데이트 요청:{}"+updateBoard);
		int updatedRows = bm.updateBoard(updateBoard);
		if (updatedRows > 0) {
		System.out.println("게시물이 업데이트되었습니다.");
			return true; // 업데이트 성공
		} else {
			System.out.println("게시물 업데이트에 실패했습니다.");
			return false; // 업데이트 실패
		}
	}
	 @Override
	  public AndroidBoardDTO selectBoardDetail(Long idx) {
		 bm.view(idx);
	    return bm.selectBoardDetail(idx);
	  }
	 @Override
		public boolean deleteBoard(Long idx) {
			int queryResult = 0;
			AndroidBoardDTO board = bm.selectBoardDetail(idx);
			
			if (board != null && "n".equals(board.getDeleteYn())) {
				queryResult = bm.deleteBoard(idx);
			}
			
			return (queryResult == 1) ? true : false;
		}
		
// ---------------------------------------------------------------------		
	@Override
	public List<BoardDTO> getBoardList2(BoardDTO board) {
		List<BoardDTO> boardList = Collections.emptyList();
	    int boardTotalCount = bm.selectBoardTotalCount2(board);
	    PaginationInfo paginationInfo = new PaginationInfo(board);
	    paginationInfo.setTotalRecordCount(boardTotalCount);

	    board.setPaginationInfo(paginationInfo);

	    if (boardTotalCount > 0) {
	      boardList = bm.selectBoardList2(board);
	    }
	    return boardList;
	}
	
	@Override
	public boolean insertBoard(BoardDTO params) {
		bm.insertBoard(params);
		return false;
	}


	@Override
  public boolean registerBoard(BoardDTO params) {
    int queryResult = 0;

    if (params.getIdx() == null) {
      queryResult = bm.insertBoard(params);
    } else {
      queryResult = bm.updateBoard(params);
    }
    return (queryResult == 1) ? true : false;
  }
	
	 
	 @Override
		public boolean updateBoard2(BoardDTO updatedBoard) {
			// 게시물 업데이트 로직
			System.out.println("게시물 업데이트 요청:{}"+updatedBoard);
			int updatedRows = bm.updateBoard(updatedBoard);
			if (updatedRows > 0) {
			System.out.println("게시물이 업데이트되었습니다.");
				return true; // 업데이트 성공
			} else {
				System.out.println("게시물 업데이트에 실패했습니다.");
				return false; // 업데이트 실패
			}
		}

	 @Override
	  public BoardDTO selectBoardDetail2(Long idx) {
		 bm.view(idx);
	    return bm.selectBoardDetail2(idx);
	  }
}
