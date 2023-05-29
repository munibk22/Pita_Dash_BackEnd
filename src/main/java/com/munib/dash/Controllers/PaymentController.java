package com.munib.dash.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.munib.dash.Services.PaymentService;
import com.munib.dash.models.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/payment")
@CrossOrigin("*")
public class PaymentController {

	PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	// Create a Gson object
	private static Gson gson = new Gson();
	private static Dotenv dotenv = Dotenv.load();

//	@Value("${PUBLISH_KEY}")
//	private String publishableKey;
//	@Value("${STRIPE_SECRET_KEY}")
//	private String stripeSecretKey;
	
	private String publishableKey = dotenv.get("STRIPE_PUBLIS_KEY"); ;
	private String stripeSecretKey = dotenv.get("STRIPE_SECRET_KEY");
	
	
//	@Value("${PUBLISH_KEY_TEST}")
//	private String publishKeyTest;
//	@Value("${STRIPE_SECRET_KEY_TEST}")
//	private String stripeSecretKeyTest;
	

	

	@PostConstruct
	public void init() {
//		Stripe.apiKey = stripeSecretKeyTest;
//		Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY");
		Stripe.apiKey = dotenv.get("STRIPE_SECRET_KEY_TEST");
//		Stripe.apiKey = stripeSecretKey;
	}
	@GetMapping(value = "/config")
	public String getPublishKey() {
//		return publishKeyTest;
//		return publishableKey;
		return dotenv.get("PUBLISH_KEY_TEST");
	};


	static class CreatePayment {
		@SerializedName("items")
		Object[] items;

		public Object[] getItems() {
			return items;
		}
	}

	static class CreatePaymentResponse {
		private String clientSecret;

		public CreatePaymentResponse(String clientSecret) {
			this.clientSecret = clientSecret;
		}
	}

	static int calculateOrderAmount(Object[] items) {
		// Replace this constant with a calculation of the order's amount
		// Calculate the order total on the server to prevent
		// people from directly manipulating the amount on the client
		return 1400;
	}


	@PostMapping("/create-payment-intent")
	public String createPaymentIntent(@RequestBody PaymentRequest paymentRequest) throws StripeException {
		System.out.println("Calling Intent Controller");
		PaymentIntent paymentIntent = null;
		
		try {			
			String stringValue = paymentRequest.getAmount();
			double doubleValue = Double.parseDouble(stringValue); // Parse back to double
			Long amount = Math.round(doubleValue * 100);
			System.out.println(amount);
			System.out.println(paymentRequest);
			
			paymentIntent = paymentService.createPaymentIntent(amount, paymentRequest.getCurrency(),
					paymentRequest.getStripeEmail(), paymentRequest.getDescription());
			System.out.println(paymentIntent);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (StripeException e) {
			e.printStackTrace();
		}

		CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
//		return ResponseEntity.ok(paymentResponse);
		return gson.toJson(paymentResponse);
	};

	@PostMapping(value = "/create-payment-intent2")
	public String createPaymentIntent2(@RequestBody CreatePayment createPayment) throws StripeException {

		for (Object element : createPayment.getItems()) {
			System.out.println(element);
		}
		// CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
		PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//				.setAmount(new Long(calculateOrderAmount(createPayment.getItems())))
//				.setAmount(itemAmount)
				.setCurrency("usd").setAutomaticPaymentMethods(
						PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
				.build();

		// Create a PaymentIntent with the order amount and currency
		PaymentIntent paymentIntent = PaymentIntent.create(params);
		System.out.println(paymentIntent);
		CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
		return gson.toJson(paymentResponse);
	}

//	@RequestMapping("/checkout")
//	public String checkout(Product model) {
//		model.setAmount(50 * 100); // in cents
////	        model.addAttribute("stripePublicKey", stripePublicKey);
////	        model.addAttribute("currency", ChargeRequest.Currency.EUR);
//		return "checkout";
//	}

	@PostMapping("/charge2")
	public Charge charge(@RequestParam String token, @RequestParam int amount) throws Exception {
		return paymentService.charge(token, amount);
	}

	@PostMapping
	public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws StripeException {
		String chargeId = paymentService.charge2(request);
		return chargeId != null ? new ResponseEntity<String>(chargeId, HttpStatus.OK)
				: new ResponseEntity<String>("Please check the credit card details entered", HttpStatus.BAD_REQUEST);
	}

//	  @PostMapping("/charge3")
//	  public ResponseEntity<String> charge(@RequestBody ChargeRequest chargeRequest) {
//	    try {
//	      Stripe stripe = paymentService.getStripe();
//
//	      Map<String, Object> params = new HashMap<>();
//	      params.put("amount", chargeRequest.getAmount());
//	      params.put("currency", chargeRequest.getCurrency());
//	      params.put("source", chargeRequest.getToken().getId());
//
//	      Charge charge = stripe.charges().create(params);
//
//	      return ResponseEntity.ok(charge.getId());
//	    } catch (Exception e) {
//	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//	    }
//	  }
//	
	@ExceptionHandler
	public String handleError(StripeException ex) {
		return ex.getMessage();
	}

}
