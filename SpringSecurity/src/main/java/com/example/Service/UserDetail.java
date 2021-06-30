package com.example.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Model.RegistrationModel;


public class UserDetail  implements UserDetails{
	private static final long serialVersionUID = -5584720112233431845L;
	private RegistrationModel Registrationmodel;
	
	private RegistrationModel registrationModel;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("grant");
		System.out.println(Registrationmodel.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRole())).collect(Collectors.toList()));
		return Registrationmodel.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRole())).collect(Collectors.toList());
	}
	
	@Override
	public String getUsername() {
		return Registrationmodel.getEmail();
	}
	
	@Override
	public String getPassword() {
		return Registrationmodel.getPassword();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/*
	 * public void setEmail(RegistrationModel Registrationmodel) {
	 * this.Registrationmodel = Registrationmodel; }
	 */
	public RegistrationModel getRegistrationmodel() {
		return Registrationmodel;
	}

	public void setRegistrationmodel(RegistrationModel Registrationmodel) {
		this.Registrationmodel = Registrationmodel;
	}
}
