package com.munib.dash.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationDTO {
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String phone;
	}
