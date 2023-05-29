package com.munib.dash.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.munib.dash.models.PaymentRequest;
import com.munib.dash.models.PaymentRequest.Currency;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {
	
//	@Value("${STRIPE_SECRET_KEY}")
//	private String secretKey;
//	
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = secretKey;
//    }
//    

    public Charge charge(String token, int amount) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");
        params.put("source", token);
        return Charge.create(params);
    }

    
	public String charge2(PaymentRequest chargeRequest) throws StripeException {
		 Map<String, Object> chargeParams = new HashMap<>();
	     chargeParams.put("amount", chargeRequest.getAmount());
	     chargeParams.put("currency", PaymentRequest.Currency.INR);
	     chargeParams.put("source", chargeRequest.getToken().getId());
	     
		Charge charge = Charge.create(chargeParams);
		return charge.getId();
	}


	public PaymentIntent createPaymentIntent(Long amount, Currency currency, String stripeEmail, String description) throws StripeException {
		System.out.println("Calling Intent Service");
//		List<Object> paymentMethodTypes = 	  new ArrayList<>();
//				paymentMethodTypes.add("card");
//				Map<String, Object> params = new HashMap<>();
//				params.put("amount", amount);
//				params.put("currency", currency); //ex- usd, inr, aud, etc);
//				params.put(
//				  "payment_method_types",
//				  paymentMethodTypes
//				);
				System.out.println(amount);
				System.out.println(Long.valueOf(amount).getClass());
		PaymentIntentCreateParams params =
						  PaymentIntentCreateParams.builder()
						    .setAmount(amount)
						    .setCurrency("USD")
//						    .setConfirm(true)
						    .setCustomer(stripeEmail)
						    .setDescription(description)
						    .setAutomaticPaymentMethods(
							PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
							.build();

		return PaymentIntent.create(params);
	}



	
	
}
