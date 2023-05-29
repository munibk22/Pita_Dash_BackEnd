package com.munib.dash.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.munib.dash.Daos.CustomerRepository;
import com.munib.dash.models.CustomerModel;
import com.munib.dash.models.Role;

@Service
public class CustomerService implements UserDetailsService{

	@Autowired
	CustomerRepository customerDao;
	
	@Autowired
	private PasswordEncoder encoder;  
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("In the user detail service ");
		return customerDao.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}
}
