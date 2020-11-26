package jha.stopcovid.geolocation;

import jha.stopcovid.geolocation.services.GeolocationService;
import jha.stopcovid.notification.services.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class geolocationTestsServices {

	@Test
	void checkIfDistanceIsValid() throws Exception{
		Assertions.assertTrue(GeolocationService.distanceIsValid(43.63751, 3.859793, 43.637168, 3.859904));
	}

	@Test
	void checkIfDistanceIsNotValid() throws Exception{
		Assertions.assertFalse(GeolocationService.distanceIsValid(43.63751, 3.859793, 43.635634, 3.869515));
	}

}
