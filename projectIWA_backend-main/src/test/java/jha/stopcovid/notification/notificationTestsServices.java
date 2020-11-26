package jha.stopcovid.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import jha.stopcovid.geolocation.controllers.GeolocationController;
import jha.stopcovid.geolocation.models.GeolocationData;
import jha.stopcovid.geolocation.services.GeolocationService;
import jha.stopcovid.notification.services.EmailService;
import jha.stopcovid.user.models.User;
import jha.stopcovid.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class notificationTestsServices {

	@Autowired
	private EmailService emailService;


	@Test
	void sendEmailTestAddressInvalid() throws Exception{
		Date date = new Date();
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
				() -> {emailService.sendMail("ds", date.toString());});
		assertTrue(exception.getMessage().contains("email address invalid"));
	}

}
