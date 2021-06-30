package com.example.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

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
public class ChangePassController {
	
	 @Autowired
	    private RegisterRepo Registerrepo;
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	//open index
	@RequestMapping("/forgotpassword")
	public String registerpage() {
		return "forgotpassword";
	 }
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String saveRegisterPage(@Validated @ModelAttribute("user") RegistrationModel register,  BindingResult result, Model model,HttpServletRequest request){
		
		    String newmob = request.getParameter("newmob");
		    String newpass = request.getParameter("newpassword");
		    System.out.println("mobile======  "+newmob+"  Paass=========  "+newpass);
			
			Optional<RegistrationModel> registermodel = Registerrepo.findByMobile(newmob);
			RegistrationModel registerdata = registermodel.get(); 
	        System.out.println("getdata   "+registerdata.getMobile());
			if(!registerdata.getMobile().equals(null)) {
				String encryptPwd = passwordEncoder.encode(newpass);
				registerdata.setPassword(encryptPwd);
				registerdata.setTemppass(newpass);
				Registerrepo.save(registerdata);
			}
			 return "loginpage";
	}
}
