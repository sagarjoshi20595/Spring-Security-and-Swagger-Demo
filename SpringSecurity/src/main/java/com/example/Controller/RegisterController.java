package com.example.Controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Model.RegistrationModel;
import com.example.Model.RoleModel;
import com.example.Repository.RegisterRepo;

@Controller

public class RegisterController {
	 @Autowired
	    private RegisterRepo Registerrepo;
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	     //open index
			@RequestMapping("/")
			public String page() {
				return "loginpage";
			 }
			//open index
			@RequestMapping("/loginpage")
			public String loginpage() {
				return "loginpage";
			 }
			
			@SuppressWarnings("serial")
			@RequestMapping(value = "/register", method = RequestMethod.POST)
			public String saveRegisterPage(@Validated @ModelAttribute("user") RegistrationModel register,  BindingResult result, Model model,HttpServletRequest request){
				
				long registerrepo=Registerrepo.countByEmail(register.getEmail());
				
				if(registerrepo != 0)
	        	 {
					model.addAttribute("message", "EmailId already Exist");
	             		
	        		 return "register";
	        	 }
				else {
					RoleModel role = new RoleModel();
					 role.setRole("user");
					 register.setRoles(new HashSet<RoleModel>() {{add(role);}});
						
					 register.setTemppass(request.getParameter("password"));
					 String pwd = register.getPassword();
					 System.out.println("password......"+pwd);
					 String encryptPwd = passwordEncoder.encode(pwd);
					 register.setPassword(encryptPwd);
					 System.out.println(register);
					 Registerrepo.save(register);
					 model.addAttribute("message", "Registration Successful");
					 return "register";
				}
				
			}
			
			//open index
			@RequestMapping("/registerpage")
			public String registerpage() {
				return "register";
			 }
			
			
			
			
}
