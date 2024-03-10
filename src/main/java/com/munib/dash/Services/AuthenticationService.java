package com.munib.dash.Services;

import javax.security.auth.login.LoginException;

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
import com.munib.dash.models.RegistrationDTO;
import com.stripe.exception.StripeException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AuthenticationService {

	@Autowired
	private RoleDao roleRepository;
	private CustomerRepository customerRepository;
	private StripeController stripeController;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;
	private Throwable currentMethod;
	
	@Autowired
	public AuthenticationService(CustomerRepository customerRepository,StripeController stripeController,
	PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager, TokenService tokenService) {
		this.customerRepository = customerRepository;
		this.stripeController = stripeController;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
		this.currentMethod = new Throwable();
	}
	

	public CustomerModel registerCustomer(RegistrationDTO registrationBody) {
		log.info("Inside AuthenticationService Method = " + currentMethod.getStackTrace()[0].getMethodName() 
				+" {} cutomer = ",registrationBody);
		try {
		String encodedPW = passwordEncoder.encode(registrationBody.getPassword());
		registrationBody.setPassword(encodedPW);
//		Role userRole = roleRepository.findByAuthority("USER").get();
//		Set<Role> authorities = new HashSet<>();
//		authorities.add(userRole);
		CustomerModel customer = customerRepository.save(new CustomerModel(registrationBody));
		CustomerModel data = stripeController.index(customer);
		customer.setStripeId(data.getStripeId());		
			return customer;
		} catch (StripeException e) {
			log.error("Error inside AuthenticationService Method = " + currentMethod.getStackTrace()[0].getMethodName());
			e.printStackTrace();
			return null;
		}
	}

	  public LoginResponseDTO loginUser(String username, String password) throws LoginException {
		  log.info("Inside AuthenticationService Method = " + currentMethod.getStackTrace()[0].getMethodName() 
				  +" {} cutomer = ",username);
	        try{
	            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	            String token = tokenService.generateJwt(auth);
	            return new LoginResponseDTO(customerRepository.findByUserName(username).get(), token);
	        } catch(AuthenticationException e){
	        	log.error("Error inside AuthenticationService Method = " + currentMethod.getStackTrace()[0].getMethodName());
	        	e.printStackTrace();
	            return new LoginResponseDTO(null, "");
	        }
	    }

}
