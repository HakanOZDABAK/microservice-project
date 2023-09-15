package com.hakanozdabak.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hakanozdabak.productservice.business.requests.ProductRequest;
import com.hakanozdabak.productservice.dataAccess.abstracts.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;
	@Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
	  dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
	  dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	  dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }
	@Test
	void shouldCreateProduct() throws Exception {
	  ProductRequest productRequest = getProductRequest();
	  String productRequestString =   objectMapper.writeValueAsString(productRequest);
	  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/add")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(productRequestString))
			  .andExpect(MockMvcResultMatchers.status().isCreated());
      Assertions.assertEquals(1,productRepository.findAll().size());
  }

	private ProductRequest getProductRequest() {
	 return ProductRequest.builder()
			 .productName("Iphone 13")
			 .category("Telephone")
			 .price(Float.valueOf(35000))
			 .build();


  }

}
