package jha.stopcovid.user;

import jha.stopcovid.user.models.User;
import jha.stopcovid.user.repositories.UserRepository;
import jha.stopcovid.user.services.UserService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class userTestsRepository {

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
	void getUserByIdTestUserNotExists() throws Exception{
		Assertions.assertNull(userRepository.getUserById("74b4a039-4b17-462c-ab68-fc4f208a504s"));
	}

	@Test
	void getUserByIdTestUserExists() throws Exception{
		Assertions.assertNotNull(userRepository.getUserById("74b4a039-4b17-462c-ab68-fc4f208a504d"));
	}


}
