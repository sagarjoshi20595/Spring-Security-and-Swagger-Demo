package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.Model.RegistrationModel;
import com.example.Repository.RegisterRepo;
@Component
public class UserDetailServices implements UserDetailsService {
	@Autowired
	private RegisterRepo Registerrepo;
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		System.out.println("in if condition"+username);
		RegistrationModel register = Registerrepo.findByEmail(username);
		System.out.println("register"+register);
		System.out.println("in if condition find email"+Registerrepo.findByEmail(username));
		UserDetail userDetails=null;
		if(register != null) {
			userDetails=new UserDetail();
			userDetails.setRegistrationmodel(register);
		}else {
			System.out.println("in else condition"+username);
			throw new UsernameNotFoundException("User not exist with this name : "+username);
		}
		return userDetails;
	}
}
