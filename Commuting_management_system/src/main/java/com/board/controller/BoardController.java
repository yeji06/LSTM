package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardDTO;
import com.board.domain.CommentDTO;
import com.board.domain.UserDTO;
import com.board.service.BoardService;
import com.board.service.CommentService;
import com.board.util.UiUtils;

@Controller
public class BoardController  extends UiUtils {

  @Autowired
  CommentService commentService;
  
  @PostMapping("/board/web/comment/write")
 	public String insertComment(@ModelAttribute("comment") final CommentDTO commentDto,
 								HttpSession session, Model model, RedirectAttributes ra) {

	  try {
	  UserDTO loggedInUser = (UserDTO)session.getAttribute("loggedInUser");
	  
	  commentDto.setCommentNickname(loggedInUser.getNickname());
	  commentService.registerComment(commentDto);
	  return "redirect:/board/web/view?idx="+commentDto.getBoardIdx();
	  } catch(NullPointerException e) {
		  ra.addFlashAttribute("message", "전송에 실패하였습니다.");
	  } catch (DataAccessException e) {
		  ra.addFlashAttribute("message", "전송에 실패하였습니다.");
	  } catch (Exception e) {
		  ra.addFlashAttribute("message", "전송에 실패하였습니다.");
	  }
	  return "redirect:/board/web";
  }
  @RequestMapping("/board/web/comment/delete")
 	public String deleteComment(@ModelAttribute("comment") final CommentDTO commentDto,
 	  @RequestParam(value = "commentId", required = false) Long commentId, Model model,HttpSession session) {
	
	  UserDTO user = (UserDTO)session.getAttribute("loggedInUser");
	  System.out.println(user.getNickname());
	  System.out.println(commentDto.getCommentNickname());
	  try {
		  if( !commentDto.getCommentNickname().equals(user.getNickname()) ){
		 	  return "redirect:/board/web/view?idx="+commentDto.getBoardIdx();
		  	}
		  if (commentId == 0) {
	 		return "redirect:/board/web/view?idx="+commentDto.getBoardIdx();
		  	}
	  } catch(NullPointerException n) {
		  return "redirect:/";
	  }
 	  commentService.deleteComment(commentId);
      return "redirect:/board/web/view?idx="+commentDto.getBoardIdx();
 	}
  
//-------------------------------------------------------------------------------------------  
  @Autowired
  BoardService boardService;
  
  @GetMapping("/board/web") 
  public String board(@ModelAttribute("params") final BoardDTO params, Model model){
	 
	  List<BoardDTO> boardList = boardService.getBoardList2(params);
      model.addAttribute("boardList", boardList);
	  return "project/공통/게시판";
	  
  }
  @GetMapping("/board/web/view") 
  public String boardDetail(@ModelAttribute("params") final BoardDTO params,
		  					@ModelAttribute("comment") final CommentDTO comment,
		  @RequestParam(value = "idx", required = false) Long idx, Model model) {
	 
		   if (idx == 0) {
	        model.addAttribute("errorMessage", "올바르지 않은 접근입니다.");
		    return "project/공통/게시판";
	        }
	       BoardDTO board = boardService.selectBoardDetail2(idx);
		
		   List<CommentDTO> comments = commentService.getCommentList2(idx);
		   if (board == null || "Y".equals(board.getDeleteYn())) {
	        model.addAttribute("errorMessage", "없는 게시글이거나 이미 삭제된 게시글입니다.");
	        return "project/공통/게시판";
		    }
		   model.addAttribute("comments", comments);
		   model.addAttribute("board", board);
	       return "project/공통/게시글상세";  
  }
  @GetMapping("/board/web/write") 
  public String writeform(@ModelAttribute("params") final BoardDTO params,
		  @RequestParam(value = "idx", required = false) Long idx, Model model) {
	  
	  if (idx == null) {
		  model.addAttribute("board", new BoardDTO());
		} else {
		  BoardDTO board = boardService.selectBoardDetail2(idx);
		  if (board == null || "Y".equals(board.getDeleteYn())) {
			  model.addAttribute("errorMessage", "없는 게시글이거나 이미 삭제된 게시글입니다.");
			  return "project/공통/게시판";
		  }
		  model.addAttribute("board", board);
		}
	  	return "project/공통/게시글작성";
	  
  }
  @PostMapping("/board/web/register") 
  public String write(@ModelAttribute("params") final BoardDTO params, Model model, HttpSession session, RedirectAttributes ra) {
	  try {
		  UserDTO loggedInUser = (UserDTO)session.getAttribute("loggedInUser");
		  session.setAttribute("loginUser", loggedInUser);
		  params.setNickname(loggedInUser.getNickname());
		  try {
			  boardService.registerBoard(params);
			  ra.addFlashAttribute("message", "전송에 성공하였습니다.");
		  } catch (DataAccessException e) {
			  ra.addFlashAttribute("message", "전송에 실패하였습니다.");
		  } catch (Exception e) {
			  ra.addFlashAttribute("message", "전송에 실패하였습니다.");
		  }
	  } catch (NullPointerException n) {
		  ra.addFlashAttribute("message", "전송에 실패하였습니다.");
		  return "redirect:/board/web";
	  }
	  return "redirect:/board/web";
  }

    @RequestMapping("/board/web/delete")
	public String deleteBoard(@ModelAttribute("params") BoardDTO params,
	  @RequestParam(value = "idx", required = false) Long idx, Model model, HttpSession session) { 
	if (idx == 0) {
		model.addAttribute("errorMessage", "올바르지 않은 접근입니다.");
		return "redirect:/board/web/view";
	}
	  UserDTO user = (UserDTO)session.getAttribute("loggedInUser");
	  System.out.println(user.getNickname());
	  System.out.println(params.getNickname());
	  
	  try { 
		  if( !params.getNickname().equals(user.getNickname()) ){
		    try {
			  boolean isDeleted = boardService.deleteBoard(idx);
			  
			  if (isDeleted == false) {
				  model.addAttribute("errorMessage", "게시글 삭제에 실패하였습니다.");
				  return "redirect:/board/web/view";
			  }
			} catch (DataAccessException e) {
				model.addAttribute("errorMessage", "데이터베이스 처리 과정에 문제가 발생하였습니다.");
				return "redirect:/board/web/view";
			} catch (Exception e) {
				model.addAttribute("errorMessage", "시스템에 문제가 발생하였습니다.");
				return "redirect:/board/web/view";
			}
		   }
	  } catch(NullPointerException n) {
		  return "redirect:/board/web";
	  }
	  model.addAttribute("successMessage", "게시글 삭제가 완료되었습니다.");
	  return "redirect:/board/web"; 
    }
	
   }


