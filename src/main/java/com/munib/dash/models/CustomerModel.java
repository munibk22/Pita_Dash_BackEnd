package com.munib.dash.models;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel implements UserDetails{

  /**
	 * 
	 */
	private static final long serialVersionUID = -2780711269963546409L;
	
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="user_id")
  private int id;
  private String firstName;
  private String lastName;
  @Column(unique=true)
  private String userName;
  private String password;
  private String email;
  private String address;
  private String zipCode;
  private String phone;
  String stripeId;
  
  @ManyToMany(fetch=FetchType.EAGER)
  @JoinTable(
		  name = "user_role_junction",
		  joinColumns = {@JoinColumn(name="user_id")},
		  inverseJoinColumns = {@JoinColumn(name="role_id")}
		  )
  private Set<Role> authorities;
  
  
  public void setEmail() {
	  this.email = this.userName;
  }
  
  public String getEmail() {
	 return this.userName;
  }
  
  public String getName () {
	  return this.firstName+" "+this.lastName;
  }

 
//  @Override
//  public String toString() {
//    return String.format(
//        "Customer[id=%d, firstName='%s', lastName='%s']",
//        id, firstName, lastName);
//  }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return this.authorities;
}


@Override
public String getPassword() {
	return this.password;
}

@Override
public String getUsername() {
	return this.userName;
}



@Override
public boolean isAccountNonExpired() {
	return true;
}

@Override
public boolean isAccountNonLocked() {
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	return true;
}

@Override
public boolean isEnabled() {
	return true;
}



public CustomerModel(String firstName, String lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
}

public CustomerModel(int i, String string, String encode) {
	this.id= i;
	this.userName = string;
	this.password = encode;
}

//public Customer(String userName2, String encodedPW, Set<Role> authorities2, String firstName2, String lastName2, String address2, String zipCode2, String phone2) {
//	
//}

public CustomerModel(String userName2, String encodedPW, Set<Role> authorities2) {
	this.userName = userName2;
	this.password = encodedPW;
	this.authorities = authorities2;
}

public CustomerModel(String userName2, String encodedPW, Set<Role> authorities2,
		String firstName,String lastName,String address) {
	this.userName = userName2;
	this.password = encodedPW;
	this.authorities = authorities2;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
}


public CustomerModel(String userName, String password, Set<Role> authorities,
		String firstName, String lastName, String address,	String zipCode, String phone) {
	super();	
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.password = password;
	this.address = address;
	this.zipCode = zipCode;
	this.phone = phone;
	this.authorities = authorities;
}


 



}