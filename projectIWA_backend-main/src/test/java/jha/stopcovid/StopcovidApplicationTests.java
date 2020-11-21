package jha.stopcovid;

import com.fasterxml.jackson.databind.ObjectMapper;
import jha.stopcovid.controllers.KafkaController;
import jha.stopcovid.controllers.UserController;
import jha.stopcovid.models.GeolocationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions.*;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class StopcovidApplicationTests {

	@Autowired
	private KafkaController controllerKafka;

	@Autowired
	private UserController controllerUser;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void publishRequestTestSucceed() throws Exception{
		String json = asJsonString(new GeolocationData("74b4a039-4b17-462c-ab68-fc4f208a504d", 43.63496, 3.873338, (long) 1605886929));
		mockMvc.perform(post("/publish").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}

	@Test
	void publishRequestTestBadBody() throws Exception{
		String json = asJsonString(new GeolocationData("74b4a039-4b17-462c-ab68-fc4f208a504d", 43.63496, 3.873338, (long) 1605886929));
		mockMvc.perform(post("/publish").contentType(MediaType.APPLICATION_JSON).content("{toto: toto2}")).andExpect(status().isBadRequest());
	}

	@Test
	void usersRequestTestUserNotExists() throws Exception{
		mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a504d").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Assertions.assertThrows(NestedServletException.class,
				() -> {mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a504d").contentType(MediaType.APPLICATION_JSON));});
	}

	@Test
	void usersRequestTestUserSucceed() throws Exception{
		mockMvc.perform(put("/users/74b4a039-4b17-462c-ab68-fc4f208a504d").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void usersRequestTestBadParam() throws Exception{
		Assertions.assertThrows(NestedServletException.class,
				() -> {mockMvc.perform(put("/users/ghj").contentType(MediaType.APPLICATION_JSON));});
	}

	private String asJsonString(Object obj){
		try{
			return new ObjectMapper().writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
