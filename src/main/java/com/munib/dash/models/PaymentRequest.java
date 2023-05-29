package com.munib.dash.models;



import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
	  @SerializedName("items")
	  Object[] items;
	
	public enum Currency{
		INR,USD;
	}
	    private String amount;
	    private Currency currency;
	    private String stripeEmail;
	    private String description;
	    private Token token;
	    private String name;

}
