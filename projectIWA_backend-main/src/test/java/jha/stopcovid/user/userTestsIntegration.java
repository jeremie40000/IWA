package jha.stopcovid.user;

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
class userTestsIntegration {

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
	void putUsersRequestTestUserNotExists() throws Exception{
		Exception exception = Assertions.assertThrows(NestedServletException.class,
				() -> {mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a50as").contentType(MediaType.APPLICATION_JSON));});
		assertTrue(exception.getCause().toString().contains("java.lang.NullPointerException"));
		assertTrue(exception.getMessage().contains("user does not exist"));
	}

	@Test
	void putUsersRequestTestUserAlreadyPositive() throws Exception{
		mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a504d").contentType(MediaType.APPLICATION_JSON));
		Exception exception = Assertions.assertThrows(NestedServletException.class,
				() -> {mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a504d").contentType(MediaType.APPLICATION_JSON));});
		assertTrue(exception.getCause().toString().contains("java.lang.IllegalArgumentException"));
		assertTrue(exception.getMessage().contains("user is already positive"));
	}

	@Test
	void putUsersRequestTestUserSucceed() throws Exception{
		mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a504d").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void putUsersRequestTestBadParam() throws Exception{
		Exception exception = Assertions.assertThrows(NestedServletException.class,
				() -> {mockMvc.perform(put("/users/ghj").contentType(MediaType.APPLICATION_JSON));});

		assertTrue(exception.getMessage().contains("id not valid"));
	}

	private String asJsonString(Object obj){
		try{
			return new ObjectMapper().writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
