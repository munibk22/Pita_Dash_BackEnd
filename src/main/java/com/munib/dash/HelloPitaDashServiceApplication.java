package com.munib.dash;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloPitaDashServiceApplication {
	
//	@Autowired
//	CustomerRepository repository;
	
	 // private static final Logger log = LoggerFactory.getLogger(HelloPitaDashServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloPitaDashServiceApplication.class, args);
	}

	
//	  @Bean
//	  public CommandLineRunner demo(RoleDao roleDao, CustomerRepository repository, PasswordEncoder passwordEncoder) {
//	    return (args) -> {
//	    	Dotenv dotenv = Dotenv.configure().load();
//	    	String testKey = dotenv.get("TEST_KEY");
//	      // save a few customers
////	      repository.save(new Customer("Jack", "Bauer"));
////	      repository.save(new Customer("Chloe", "O'Brian"));
////	      repository.save(new Customer("Kim", "Bauer"));
////	      repository.save(new Customer("David", "Palmer"));
//
//System.out.println("In CommandLineRunner");
//System.out.println(testKey);
//	    	if(roleDao.findByAuthority("ADMIN").isPresent()) return;
//	    	
//	    	Role adminRole = roleDao.save(new Role("ADMIN"));
//	    	roleDao.save(new Role("USER"));
//
//	    	Set<Role> roles = new HashSet<>();
//	    	roles.add(adminRole);
//	    	
//	    	CustomerModel admin = new CustomerModel(1,"admin",passwordEncoder.encode("password"));
//	    	
////	    	repository.save(admin);
//	      // fetch all customers
//	      log.info("Customers found with findAll():");
//	      log.info("-------------------------------");
//	      for (CustomerModel customer : repository.findAll()) {
//	        log.info(customer.toString());
//	      }
//	      log.info("");
//
//	      // fetch an individual customer by ID
//	      CustomerModel customer = repository.findById(502);
//	      log.info("Customer found with findById(1):");
//	      log.info("--------------------------------");
//	      String name = Optional.ofNullable(customer.getFirstName()).orElse("test");
//	      log.info(name);
//	      log.info("");
//
//	      // fetch customers by last name
//	      log.info("Customer found with findByLastName('Bauer'):");
//	      log.info("--------------------------------------------");
//	      repository.findByLastName("Bauer").forEach(bauer -> {
//	        log.info(bauer.toString());
//	      });
//	       for (CustomerModel bauer : repository.findByLastName("Bauer")) {
//	        log.info(bauer.toString());
//	      // }
//	      log.info("");
//	    };
//	  };
//	  }

	  
}
