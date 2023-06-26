package com.munib.dash.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munib.dash.Controllers.StripeController;
import com.munib.dash.Daos.CustomerRepository;
import com.munib.dash.Daos.RoleDao;
import com.munib.dash.models.CustomerModel;
import com.munib.dash.models.LoginResponseDTO;
import com.munib.dash.models.Role;
import com.stripe.exception.StripeException;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private StripeController stripeController;

	@Autowired
	private RoleDao roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
//	registrationBody.getPhone(),
//	registrationBody.getUserName(),
//	registrationBody.getPassword()
//	,registrationBody.getFirstName(),
//	registrationBody.getLastName(),
//	registrationBody.getAddress(),
//	registrationBody.getZipCode(),
//	registrationBody.getId()
	public CustomerModel registerCustomer(String userName, String password,String firstName,
		String lastName,String address,String zipCode, String phone, int id
		) {
		String encodedPW = passwordEncoder.encode(password);
//		Role userRole = roleRepository.findByAuthority("USER").get();
//		Set<Role> authorities = new HashSet<>();
//		authorities.add(userRole);
		try {
		CustomerModel customer = customerRepository.save(new CustomerModel(userName, encodedPW
				,firstName, lastName,address, zipCode, phone,id));
		CustomerModel data = stripeController.index(customer);
		customer.setStripeId(data.getStripeId());
		System.out.println(data);
		
			return customer;
		} catch (StripeException e) {
			e.printStackTrace();
		}
		return null;
	}

	  public LoginResponseDTO loginUser(String username, String password){

	        try{
	            Authentication auth = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	            );

	            String token = tokenService.generateJwt(auth);

	            return new LoginResponseDTO(customerRepository.findByUserName(username).get(), token);

	        } catch(AuthenticationException e){
	            return new LoginResponseDTO(null, "");
	        }
	    }
}
