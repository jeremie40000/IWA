package jha.stopcovid;

import jha.stopcovid.controllers.KafkaController;
import jha.stopcovid.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StopcovidApplicationTests {

	@Autowired
	private KafkaController controllerKafka;

	@Autowired
	private UserController controllerUser;

	@Test
	void contextLoads() throws Exception{
		assertThat(controllerKafka).isNotNull();
	}

}
