package com.munib.dash.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String phone;
	}
