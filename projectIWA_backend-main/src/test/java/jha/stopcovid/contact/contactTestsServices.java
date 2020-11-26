package jha.stopcovid.contact;

import jha.stopcovid.contact.services.ContactService;
import jha.stopcovid.notification.services.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class contactTestsServices {

	@Autowired
	private ContactService contactService;


	@Test
	void checkIfDateExpiredTestReturnTrue() throws Exception{
		Assertions.assertTrue(contactService.dateExpired(new Timestamp(1606387267000L), new Timestamp(1602088000000L)));
	}

	@Test
	void checkIfDateExpiredTestReturnFalse() throws Exception{
		Assertions.assertFalse(contactService.dateExpired(new Timestamp(1606387267000L), new Timestamp(1606387067000L)));
	}

}
