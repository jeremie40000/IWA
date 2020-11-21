package jha.stopcovid.geolocation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jha.stopcovid.geolocation.controllers.GeolocationController;
import jha.stopcovid.geolocation.models.GeolocationData;
import jha.stopcovid.user.controllers.UserController;
import jha.stopcovid.user.models.User;
import jha.stopcovid.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class geolocationTests {

	@Autowired
	private GeolocationController controllerKafka;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void setup() {
		User user1 = new User("74b4a039-4b17-462c-ab68-fc4f208a504d", "toto@gmail.com", false, "1605886929");
		userRepository.saveAndFlush(user1);
	}
	@Test
	void postLocationRequestTestSucceed() throws Exception{
		String json = asJsonString(new GeolocationData("74b4a039-4b17-462c-ab68-fc4f208a504d", 43.63496, 3.873338, (long) 1605886929));
		mockMvc.perform(post("/locations").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}

	@Test
	void postLocationRequestTestBadBody() throws Exception{
		String json = asJsonString(new GeolocationData("74b4a039-4b17-462c-ab68-fc4f208a504d", 43.63496, 3.873338, (long) 1605886929));
		mockMvc.perform(post("/locations").contentType(MediaType.APPLICATION_JSON).content("{toto: toto2}")).andExpect(status().isBadRequest());
	}

	private String asJsonString(Object obj){
		try{
			return new ObjectMapper().writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
