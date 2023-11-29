package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.service.EmployeeService;
import com.board.service.UserService;
import com.board.util.UiUtils;

@Controller
public class FindController extends UiUtils {

	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/user/empNo")
	public String getEmpNoForm(@ModelAttribute("employee") EmployeeDTO employeeDTO, Model model) {
		return "project/기타/직원번호찾기";
	}
	@PostMapping("/user/getEmpNo")
	public String getEmpNo(@ModelAttribute("employee") EmployeeDTO employeeDTO, Model model) {
		EmployeeDTO params = employeeService.getEmployeeNo(employeeDTO);
		model.addAttribute("employeeNo", params);
		return "project/기타/직원번호찾기";
	}
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/user/lost")
	public String getUserForm(@ModelAttribute("params") EmployeeDTO employeeDTO, Model model) {
		return "project/기타/패스워드찾기";
	}
	@PostMapping("/user/getNickname")
	public String getNickname(@ModelAttribute("params") EmployeeDTO employeeDTO, Model model) {
		employeeDTO.setNickname(userService.getNickname(employeeDTO));
		return "project/기타/패스워드찾기";
	}
	@PostMapping("/user/setPw")
	public String setPw(@ModelAttribute("params") EmployeeDTO employeeDTO, RedirectAttributes ra) {
		UserDTO userDTO = new UserDTO();
		userDTO.setNickname(employeeDTO.getNickname());
		userDTO.setPw(employeeDTO.getPw());
		try{
			userService.setPw(userDTO);
			  ra.addFlashAttribute("successMessage", "변경이 완료되었습니다.");
		    } catch(Exception e) {
		      ra.addFlashAttribute("errorMessage", "변경에 실패하였습니다. 다시 시도해주세요.");
		    }
		return "redirect:/user/lost";
	}

}