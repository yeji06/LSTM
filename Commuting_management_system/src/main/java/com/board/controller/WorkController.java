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

import com.board.domain.UserDTO;
import com.board.domain.WorkDTO;
import com.board.service.UserService;
import com.board.service.WorkService;

@Controller
public class WorkController{
	
  @Autowired
  private WorkService workService;
  
  @Autowired
  private UserService us;
  
  @GetMapping("/ES")
  public String openWorkList(@ModelAttribute("params") WorkDTO params, Model model, HttpSession session) {
	try {
	  if(session.getAttribute("ADMIN")==null) {
		UserDTO user = (UserDTO)session.getAttribute("loggedInUser");
		int empNo = us.getEmpNo3(user.getNickname());
		params.setNo(empNo);
		List<WorkDTO> workList = workService.getWorkList2(params);
		model.addAttribute("workList", workList);
	}else {
		List<WorkDTO> workList = workService.getWorkList(params);
		model.addAttribute("workList", workList);
	}
    return "project/공통/출퇴근";
	} catch (NullPointerException n) {
		  return "project/공통/로그인";
	  }
  }
  
  @PostMapping("/ES/add")//추가 클릭시
  public String registerWork(@ModelAttribute("params") final WorkDTO params, Model model) {
    try {
      workService.registerWork(params);
    } catch (DataAccessException e) {
    } catch (Exception e) {
    }
    return "redirect:/ES";
  }
  
}
