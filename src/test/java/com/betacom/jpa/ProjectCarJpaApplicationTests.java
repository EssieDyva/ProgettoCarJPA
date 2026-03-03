package com.betacom.jpa;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controllers.BiciControllerTest;
import com.betacom.jpa.controllers.CategoriaControllerTest;
import com.betacom.jpa.controllers.ColoreControllerTest;
import com.betacom.jpa.controllers.FreniControllerTest;
import com.betacom.jpa.controllers.MacchinaControllerTest;
import com.betacom.jpa.controllers.MotoControllerTest;
import com.betacom.jpa.controllers.SospensioniControllerTest;
import com.betacom.jpa.controllers.TipoAlimentazioneControllerTest;
import com.betacom.jpa.controllers.VeicoliControllerTest;



@Suite
@SelectClasses({
	BiciControllerTest.class,
	CategoriaControllerTest.class,
	ColoreControllerTest.class,
	FreniControllerTest.class,
	MacchinaControllerTest.class,
	MotoControllerTest.class,
	SospensioniControllerTest.class,
	TipoAlimentazioneControllerTest.class,
	VeicoliControllerTest.class
})

@SpringBootTest
class ProjectCarJpaApplicationTests {

	@Test
	void contextLoads() {
	}
					 // !IMPORTANT! V
	/*!IMPORTANT! >*/public String test; /*< !IMPORTANT! */

}
