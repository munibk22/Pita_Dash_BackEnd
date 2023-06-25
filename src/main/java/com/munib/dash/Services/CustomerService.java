package com.munib.dash.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munib.dash.Daos.CustomerRepository;
import com.munib.dash.models.CustomerModel;

@Service
public class CustomerService implements UserDetailsService{

	@Autowired
	CustomerRepository customerDao;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("In the user detail service ");
		return customerDao.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}


	public List<CustomerModel> findAll() {
		System.out.println("In the user detail service:: Returning All customers ");
		return customerDao.findAll();
	}
}
