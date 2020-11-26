package jha.stopcovid.contact;

import jha.stopcovid.contact.business.ContactData;
import jha.stopcovid.contact.models.Contact;
import jha.stopcovid.contact.repositories.ContactRepository;
import jha.stopcovid.contact.services.ContactService;
import jha.stopcovid.user.models.User;
import jha.stopcovid.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class contactTestsRepository {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactService contactService;

	@BeforeEach
	public void setup() {
		User user1 = new User("74b4a039-4b17-462c-ab68-fc4f208a504d", "toto@gmail.com", false, "1605886929");
		userRepository.saveAndFlush(user1);
		User user2 = new User("74b4a039-4b17-462c-ab68-fc4f208a504e", "toto@gmail.com", false, "1605886929");
		userRepository.saveAndFlush(user2);
		User user3 = new User("74b4a039-4b17-462c-ab68-fc4f208a504f", "toto@gmail.com", false, "1605886929");
		userRepository.saveAndFlush(user3);
		ContactData contact1 = new ContactData("74b4a039-4b17-462c-ab68-fc4f208a504f", "74b4a039-4b17-462c-ab68-fc4f208a504e", "1605886929");
		contactService.addContactToDB(contact1);
		ContactData contact2 = new ContactData("74b4a039-4b17-462c-ab68-fc4f208a504d", "74b4a039-4b17-462c-ab68-fc4f208a504e", "1605886935");
		contactService.addContactToDB(contact2);
	}


	@Test
	void findAllContactsTestReturnNoContacts() throws Exception{
		Assertions.assertEquals(0, contactRepository.findAllContacts("74b4a039-4b17-462c-ab68-fc4f208a504s").size());
	}

	@Test
	void findAllContactsTestReturnTwoContacts() throws Exception{
		Assertions.assertEquals(2, contactRepository.findAllContacts("74b4a039-4b17-462c-ab68-fc4f208a504e").size());
	}

	@Test
	void contactExistsTestReturnNull() throws Exception{
		Assertions.assertNull(contactRepository.contactExist("74b4a039-4b17-462c-ab68-fc4f208a504s", "74b4a039-4b17-462c-ab68-fc4f208a504e"));
		Assertions.assertNull(contactRepository.contactExist("74b4a039-4b17-462c-ab68-fc4f208a504e", "74b4a039-4b17-462c-ab68-fc4f208a504s"));
	}

	@Test
	void contactExistsTestReturnNotNull() throws Exception{
		Assertions.assertNotNull(contactRepository.contactExist("74b4a039-4b17-462c-ab68-fc4f208a504e", "74b4a039-4b17-462c-ab68-fc4f208a504d"));
		Assertions.assertNotNull(contactRepository.contactExist("74b4a039-4b17-462c-ab68-fc4f208a504d", "74b4a039-4b17-462c-ab68-fc4f208a504e"));
	}
}
