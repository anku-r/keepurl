package com.ankur.keepurl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ankur.keepurl.manager.api.UserLinkService;

@SpringBootTest
class KeepurlApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserLinkService service;

	@Test
	void contextLoads() {
		
	}

}
