package com.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.service.EmployeeService;
import com.board.service.UserService;

@Controller
public class MainController {
  
  @GetMapping({"/","/main"}) 
  public String main() {
      return "project/공통/메인";
  }
  
  @Autowired
  public UserService userService;
  @Autowired
  public EmployeeService employeeService;
  @GetMapping("/myInfo") 
  public String myInfo(Model model, HttpSession session) {
	  try {
		  //닉네임을 사용하여 직원번호를 출력
		  UserDTO user = (UserDTO)session.getAttribute("loggedInUser");
		  long empNO = userService.getEmpNo3(user.getNickname());
		  //직원번호를 사용하여 직원상세정보를 출력
		  EmployeeDTO employee = employeeService.getEmployeeDetail(empNO);
		  model.addAttribute("employee", employee);
		  return "project/공통/내정보";
		  
	  } catch (NullPointerException n) {
		  return "redirect: /";
	  }
  }
  
  @GetMapping("/admin/on") 
  public String adminOn(HttpSession session, RedirectAttributes ra) {
	  UserDTO user = (UserDTO)session.getAttribute("loggedInUser");
	  String role = user.getRole();
	  System.out.println(role);
	 try {
	  if(role.equals("ROLE_ADMIN")) {
	   session.setAttribute("ADMIN", "ADMIN");
	   ra.addFlashAttribute("successMessage", "관리자페이지 입니다");
	   return "redirect:/";  
	  }
	  else {
	   ra.addFlashAttribute("successMessage", "관리자권한이 없습니다");
	   return "redirect:/";
	  }
	  } catch (NullPointerException e) {
	  }
	 ra.addFlashAttribute("successMessage", "관리자권한이 없습니다");
  return "redirect:/";
  }
  @GetMapping("/admin/out") 
  public String admin(HttpSession session) {
  	 session.setAttribute("ADMIN", null);
  	 return "redirect:/";
  }
}