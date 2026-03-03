package com.betacom.jpa.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.jpa.dto.inputs.ColoreRequest;
import com.betacom.jpa.dto.outputs.ColoreDTO;
import com.betacom.jpa.response.Resp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ColoreControllerTest {
	@Autowired
	private ColoreController coloreC;
	
	@SuppressWarnings("unchecked")
	@Test
	@Order(1)	
	public void myTest() {
		createColore();
		updateColore();
		deleteColore();
		list();
	}

	public void createColore() {

		log.debug("Create colore");
		ColoreRequest req = new ColoreRequest();
		req.setId(1);
		req.setDescription("123");
		ResponseEntity<Resp> resp = coloreC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("Colore creato");
		
	}

	public void list() {
		log.debug("Test list colore");
		
		ResponseEntity<?> resp = coloreC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<ColoreDTO> lS = (List<ColoreDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
	//	Assertions.assertThat(lS.get(0).getCognome()).isEqualTo("Rossi");
		lS.forEach(s -> log.debug(s.toString()));
		// updateSocio();
	}
	
	public void updateColore() {
		log.debug("******* Update colore  *******");
		
		ColoreRequest req = new ColoreRequest();
		req.setId(2);
		req.setDescription("Bianco");
	
		ResponseEntity<Resp> resp = coloreC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("Colore aggiornato");
		
				
	}

	public void deleteColore() {
		log.debug("******* delete colore  *******");
		
		ResponseEntity<Resp> resp = coloreC.delete(2);
	
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("Colore cancellato");
		
				
	}

}