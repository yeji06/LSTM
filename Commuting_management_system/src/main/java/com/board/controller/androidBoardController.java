package com.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.AndroidBoardDTO;
import com.board.domain.AndroidCommentDTO;
import com.board.domain.BoardDTO;
import com.board.domain.CommentDTO;
import com.board.mapper.BoardMapper;
import com.board.mapper.CommentMapper;
import com.board.service.BoardService;
import com.board.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class androidBoardController {
	private static final Logger logger = LoggerFactory.getLogger(ApiTransferController.class);
	@Autowired
	BoardService bs;
	@Autowired
	BoardMapper bm;
	@Autowired
	CommentMapper cm;
	@Autowired
	CommentService cs;

	@GetMapping("/board")
	public List<AndroidBoardDTO> board(@ModelAttribute("board") AndroidBoardDTO board) {
		logger.info("Fetching board data...");
		logger.info(board.content);
		List<AndroidBoardDTO> boardList = bs.getBoardList(board);
		logger.info("Board data fetched successfully.");
		return boardList;
	}

	@PostMapping("/board.do")
	public List<AndroidBoardDTO> write(@ModelAttribute("board") AndroidBoardDTO board) {
		logger.info("Writing board data...");
		List<AndroidBoardDTO> boardList = bs.getBoardList(board);
		logger.info("Board data written successfully.");
		return boardList;
	}

	@GetMapping("/board/details")
	public AndroidBoardDTO view(@ModelAttribute("board") AndroidBoardDTO board, @PathVariable("idx") Long idx) {
		logger.info("Fetching board details...");
		AndroidBoardDTO boardDetails = bs.selectBoardDetail(idx); // 게시물 상세 정보를 가져오는 메서드 호출
		logger.info("Board details fetched successfully.");
		return boardDetails;
	}

	@PostMapping("/CreatePost1")
	public void save(@RequestBody AndroidBoardDTO board) {
		System.out.println(board);
		log.info("nickname={}", board.getNickname());
		log.info("title={}", board.getTitle());
		log.info("content={}", board.getContent());
		bm.insertBoard(board);
		log.info("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");

	}

	// Spring Boot 서버에서 POST 요청을 처리하는 컨트롤러 메서드
	@PostMapping("/CreateComment")
	public void createComment(@RequestBody AndroidCommentDTO comment) {

		log.info("댓글 내용: {}", comment.getContent());
		log.info("댓글 작성자 id={}", comment.getCommentNickname());
		log.info("보드 idx={}", comment.getBoardIdx());
		cm.insertComment(comment);

		// 댓글 정보를 처리하고 저장하는 로직을 추가
		// board 객체를 활용하여 댓글 내용 등을 처리

		// 댓글 정보를 데이터베이스에 저장하는 코드
		// bm.insertComment(board);

		log.info("댓글 작성 성공");
	}

	@PostMapping("/board.delete/{postIdx}")
	public void deleteBoard(@PathVariable("postIdx") Long idx) {
		logger.info("게시물 삭제 요청, postIdx={}", idx);
		boolean code = bs.deleteBoard(idx);
		logger.info("code={}", code);
//			logger.info("update={}", board.nickname);
//			logger.info("update={}", board.idx);
//			logger.info("update={}", board.content);
//			logger.info("update={}", board.title);	

	}

	@PostMapping("board.update")
	public void updateBoard(@RequestBody AndroidBoardDTO updateBoard) {
		logger.info("게시물 업데이트 요청: {}", updateBoard);
		boolean isUpdated = bs.updateBoard(updateBoard);
		if (isUpdated) {
			logger.info("게시물이 업데이트되었습니다.");
		} else {
			logger.error("게시물 업데이트에 실패했습니다.");

			// 실패 시에 필요한 처리를 추가하세요.
		}
	}

	@GetMapping("/board/comments/{postIdx}")
	public List<AndroidCommentDTO> getComments(@PathVariable("postIdx") Long postIdx) {
		logger.info("Fetching comments for postIdx={}", postIdx);

		List<AndroidCommentDTO> comments = cs.getCommentList(postIdx);
//		logger.info("Data={}", comments.get(0));

		logger.info("Comments fetched successfully.");
		log.info("length={}", comments.size());
		return comments;
	}

	@PostMapping("/DeleteComment")
	public boolean AndroidDeleteComment(@RequestBody AndroidCommentDTO deleteCommentDTO) {
		log.info("dto={}", deleteCommentDTO.toString());
	    log.info("commentId={}", deleteCommentDTO.getCommentId());
	    log.info("commentNickname={}", deleteCommentDTO.getCommentNickname());
	    log.info("BoardIdx={}", deleteCommentDTO.getBoardIdx());
	    
	    int updateResult = cm.AndroidDeleteComment(deleteCommentDTO);
	    
	    return updateResult > 0; 
	}
}
