package com.example.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasAuthority('ROLE_user')")
@Controller
public class AfterLoginController {
	//open index
	@RequestMapping("/afterloginpage")
	public String afterloginpage() {
		return "afterloginpage";
	 }
}
