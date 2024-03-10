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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Customers")
public class CustomerModel implements UserDetails{
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
  
  public CustomerModel(RegistrationDTO registrationBody) {	
		this.userName = registrationBody.getUserName();
		this.email = registrationBody.getUserName();
		this.password = registrationBody.getPassword();
		this.firstName = registrationBody.getFirstName();
		this.lastName = registrationBody.getLastName();
		this.address = registrationBody.getAddress();
		this.zipCode = registrationBody.getZipCode();
		this.phone = registrationBody.getPhone();
  }
  
//  @ManyToMany(fetch=FetchType.EAGER)
//  @JoinTable(
//		  name = "user_role_junction",
//		  joinColumns = {@JoinColumn(name="user_id")},
//		  inverseJoinColumns = {@JoinColumn(name="role_id")}
//		  )
//  private Set<Role> authorities;
  
  
  public void setEmail() {
	  this.email = this.userName;
  }
  
  public String getEmail() {
	 return this.userName;
  }
  
  public String getName () {
	  return this.firstName+" "+this.lastName;
  }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return null;
//	return this.authorities;
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

}