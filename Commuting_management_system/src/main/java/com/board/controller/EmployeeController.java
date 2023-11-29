package com.board.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/admin/HRM") 
	public String hrm(@ModelAttribute("params") EmployeeDTO employee, Model model, HttpSession session, RedirectAttributes ra){
		try {
		UserDTO user = (UserDTO)session.getAttribute("loggedInUser");  
		String role = user.getRole();
			if(!role.equals("ROLE_ADMIN")) {
			ra.addFlashAttribute("successMessage", "관리자권한이 없습니다");
			return "redirect:/main"; }
		}catch (NullPointerException e) { 
			ra.addFlashAttribute("successMessage", "관리자권한이 없습니다");
		    return "redirect:/main"; }
	 
		  List<EmployeeDTO> employeeList = employeeService.getEmployeeList(employee);
		  model.addAttribute("employeeList", employeeList);
		  return "project/관리자/인사관리";
	}
	@GetMapping("/admin/employee/add") 
	  public String addform(@ModelAttribute("params") final EmployeeDTO params,
			  @RequestParam(value = "no", required = false) Long no, Model model,
			  							HttpSession session, RedirectAttributes ra){
		try {
		UserDTO user = (UserDTO)session.getAttribute("loggedInUser");  
		String role = user.getRole();
			if(!role.equals("ROLE_ADMIN")) {
			ra.addFlashAttribute("successMessage", "관리자권한이 없습니다");
			return "redirect:/admin/employee/add"; }
		}catch (NullPointerException e) { 
			ra.addFlashAttribute("successMessage", "관리자권한이 없습니다");
		    return "redirect:/admin/employee/add"; }
		
	    if (no == null) {
			  model.addAttribute("employee", new EmployeeDTO());
		} else {
		  EmployeeDTO employee = employeeService.getEmployeeDetail(no);
		  if (employee== null || "Y".equals(employee.getDeleteYn())) {
			  model.addAttribute("errorMessage", "없는 직원이거나 이미 삭제된 직원입니다.");
			  return "project/관리자/인사관리";
		  }
		  model.addAttribute("employee", employee);
		}
		  return "project/관리자/직원추가";
	  }
	@PostMapping("/admin/employee/cancel")
	public String deleteCelcelEmployee(@ModelAttribute("params")  EmployeeDTO params,
			@RequestParam(value = "no", required = false) Long no, Model model) {
		employeeService.deleteCancelEmployee(no);
		return "redirect:/admin/HRM";
	}
	@PostMapping("/admin/employee/delete")
 	public String deleteEmployee(@ModelAttribute("params")  EmployeeDTO params,
			  @RequestParam(value = "no", required = false) Long no, Model model) {
		employeeService.deleteEmployee(no);
		return "redirect:/admin/HRM";
 	}
	@PostMapping("/admin/employee/register") 
	public String employeeAdd(@ModelAttribute("params") final EmployeeDTO employee, RedirectAttributes ra){
		try {
              employeeService.registerEmployee(employee);
              ra.addFlashAttribute("successMessage", "전송이 완료되었습니다.");
	    } catch(Exception e) {
	      ra.addFlashAttribute("errorMessage", "직원추가에 실패하였습니다. 다시 시도해주세요.");
	    }
	  return "redirect:/admin/employee/add";
	}
//	@PostMapping("/admin/employee/register")
//    public String employeeAdd(@ModelAttribute("params") final EmployeeDTO employee, RedirectAttributes ra){
//        try {
//        	System.out.println(">>>>>>>>>>>>>1");
//            if (employee.getAuth().contains("ROLE_ADMIN")) {
//                employee.assignAdminRoleToUser();
//            } else {
//                employee.removeAdminRoleFromUser();
//            }
//            System.out.println(">>>>>>>>>>>>>2");
//            employeeService.registerEmployee(employee); 
//            ra.addFlashAttribute("successMessage", "전송이 완료되었습니다.");
//        } catch (Exception e) {
//        	System.out.println(">>>>>>>>>>>>>3");
//        	ra.addFlashAttribute("successMessage", "전송이 실패하였습니다. 다시 시도해주세요.");
//        }
//        return "redirect:/admin/employee/add";
//    }

}
