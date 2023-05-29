package com.munib.dash.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.munib.dash.models.CustomerModel;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

//@RestController
//@RequestMapping(value = "/api")
//@CrossOrigin("*")
@Service
public class StripeController {

	Dotenv dotenv = Dotenv.load();
	
//	@Value("${STRIPE_SECRET_KEY}")
//	private String stripeKey;
	
	@PostConstruct
	public void init() {
//		Stripe.apiKey = stripeSecretKeyTest;
		Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");
//		Stripe.apiKey = stripeSecretKey;
	}
	
	
//	@PostMapping(value = "/createCustomer")
	public CustomerModel index(CustomerModel data ) throws StripeException {
//		Stripe.apiKey = stripeKey;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", data.getName());
		params.put("email", data.getEmail());
		Customer customer = Customer.create(params);
		data.setStripeId(customer.getId());		
		return data;		
	}
}
