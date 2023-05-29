package com.munib.dash.models;

public class LoginResponseDTO {
	
	 private CustomerModel user;
	    private String jwt;

	    public LoginResponseDTO(){
	        super();
	    }

	    public LoginResponseDTO(CustomerModel user, String jwt){
	        this.user = user;
	        this.jwt = jwt;
	    }

	    public CustomerModel getUser(){
	        return this.user;
	    }

	    public void setUser(CustomerModel user){
	        this.user = user;
	    }

	    public String getJwt(){
	        return this.jwt;
	    }

	    public void setJwt(String jwt){
	        this.jwt = jwt;
	    }

}
