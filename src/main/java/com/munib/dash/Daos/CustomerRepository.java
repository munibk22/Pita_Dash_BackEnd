package com.munib.dash.Daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.munib.dash.models.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
	  List<CustomerModel> findByLastName(String lastName);
	  Optional<CustomerModel> findByUserName(String username);

	  CustomerModel findById(int id);
	}
	
	
//	public interface CustomerRepository  extends CrudRepository<Customer, Long> {
//
//		  List<Customer> findByLastName(String lastName);
//
//		  Customer findById(long id);
//		}