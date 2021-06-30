package com.example.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.RegistrationModel;
@Repository
public interface RegisterRepo extends JpaRepository<RegistrationModel,Integer>{

	RegistrationModel findByEmail(String username);
	long countByEmail(String email);
	Optional<RegistrationModel> findByMobile(String newmob);
	
}
