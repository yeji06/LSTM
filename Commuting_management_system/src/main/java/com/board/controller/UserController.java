package com.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.EmployeeDTO;
import com.board.domain.UserDTO;
import com.board.service.UserService;
import com.board.util.UiUtils;

@Controller
public class UserController extends UiUtils {

	@Autowired
    private UserService userService;
	
    @GetMapping("/user/signUp")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        
        String err = (String)model.getAttribute("errorMessage");
	    model.addAttribute("errorMessage", err);
        return "project/공통/회원가입";
    }
    @PostMapping("/user/signUp")
    public String signUp(@ModelAttribute("user") UserDTO user, RedirectAttributes ra, Model model) {
    	Long empNo = (long)user.getEmployeeNo();
    	EmployeeDTO params = new EmployeeDTO(); params.setNo(empNo);
    	if(userService.getEmpNo(params)) {
    		model.addAttribute("errorMessage", "존재하지 않는 직원입니다");
    		return "project/공통/회원가입";
    	}
    	if(userService.getEmpNo2(user)) {
    		model.addAttribute("errorMessage", "이미 가입된 직원입니다");
    		return "project/공통/회원가입";
    	}
    	if(userService.sameNickname(user)) {
    		model.addAttribute("errorMessage", "중복된 닉네임이 존재합니다");
    		return "project/공통/회원가입";
    	}
    	try{ 
    		userService.insertUser(user);
    		ra.addFlashAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉션
		}catch(Exception e) {
    		model.addAttribute("errorMessage", "회원가입에 실패했습니다. 다시 시도해주세요.");
    		return "project/공통/회원가입";
		}
    }
	
    @GetMapping("/user/login")
    public String showLoginForm(Model model,RedirectAttributes rr) {
    	model.addAttribute("user", new UserDTO());
    	
	    String err = (String)model.getAttribute("errorMessage");
	    model.addAttribute("errorMessage", err);
        return "project/공통/로그인";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute("user") UserDTO user, Model model,
    		HttpSession session, RedirectAttributes ra) {
        boolean loginUser = userService.loginUser(user);
        if (loginUser) {
        	user.setRole( userService.getRole(user) );
            session.setAttribute("loggedInUser", user);
            
            session.setMaxInactiveInterval(900); // 1800 = 30분

            System.out.println("로그인 성공: " + user.getNickname());
            System.out.println("로그인 성공: " + user.getPw());
            ra.addFlashAttribute("successMessage", "로그인성공");
            return "redirect:/main"; 
        } else {
            System.out.println("로그인 실패: " + user.getNickname());
            System.out.println("로그인 실패: " + user.getPw());
            model.addAttribute("errorMessage", "로그인에 실패했습니다. 다시 시도해주세요.");
            return "project/공통/로그인"; 
    }
}
    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/user/login";
    }
}