//package com.munib.dash.Controllers;
//
//import java.util.HashSet;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.munib.dash.Services.HomeService;
//
//@WebMvcTest(controllers = HomeController.class)
//@AutoConfigureMockMvc(addFilters= false)
//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//public class HomeControllerTest {
//	
//	@Autowired
//	private MockMvc mockMvc;	
//	@Autowired
//	private ObjectMapper objectMapper;
//	@MockBean
//	private HomeService homeService;
//	
//	@InjectMocks
//	private HomeController homeController;
//
//	
//	public HomeControllerTest() {
//	}
//	
////	@Before
////	public void setUp()throws Exception{
////		mockMvc = MockMvcBuilders.standaloneSetup(homeController)
////				.build();		
////	}
//	
//	@Test
//	public void homeControllerTest() throws Exception{
//		mockMvc.perform(
//				MockMvcRequestBuilders.get("/hello")
//				)
//				.andExpect(MockMvcResultMatchers.status().isOk());
////				.andExpect(MockMvcResultMatchers.content().string("Hello from HomeController"));
//	}
//	
////	@Test
////	public void helloTest() throws Exception {
////		HashSet<String> str = new HashSet<String>();
////		str.add("Hello From Test Case");
////		given(homeService.ArgumentMathcers.any().willAnswer());		
////		
////	}
//	
//	@Test
//	public void helloServiceTest() throws Exception {
////		mockMvc.perform(null)
//	}
//	
//
//}
