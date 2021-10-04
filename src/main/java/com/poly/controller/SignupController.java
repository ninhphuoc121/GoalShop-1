package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.AccountDao;
import com.poly.entity.Account;
import com.poly.service.AccountService;





@Controller
public class SignupController {
	
	@Autowired
	AccountService accservice;
	
	@Autowired
	AccountDao dao;
	
	@RequestMapping("/home/signup")
	public String Signup() {
		return "acc/signup";
	}
	
	@RequestMapping("/signupnew")
	public String signup2(Account  acc , @RequestParam("confim-Password") String confim , Model model) {
		if(!dao.existsById(acc.getUsername())) {
			if(confim.equals(acc.getPassword())) {
			acc.setPhoto("");
			accservice.create(acc);
			model.addAttribute("message","Đăng kí thành công");
			return "acc/signup";
			}
			model.addAttribute("message","Tài khoản đã có người sử dụng");
			return "acc/signup";
		}
		else {
			model.addAttribute("message","Đăng kí không thành công");
			return "acc/signup";
		}
		
	}
}
