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
import com.betacom.jpa.dto.inputs.SospensioniRequest;
import com.betacom.jpa.dto.outputs.ColoreDTO;
import com.betacom.jpa.dto.outputs.SospensioniDTO;
import com.betacom.jpa.response.Resp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SospensioniControllerTest {

	@Autowired
	private SospensioniController sospC;
	
	@SuppressWarnings("unchecked")
	@Test
	@Order(1)	
	public void myTest() {
		createSospensioni();
		updateSospensioni();
		deleteSospensioni();
//		list();
	}
	
	public void createSospensioni() {

		log.debug("Create sospensioni");
		SospensioniRequest req = new SospensioniRequest();
		req.setId(1);
		req.setDescription("Molla");
		ResponseEntity<Resp> resp = sospC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());

		Assertions.assertThat(resp.getBody().getMsg()).isEqualTo("Sospensione creata");
		
	}
	
	public void updateSospensioni() {
		log.debug("******* Update sospensioni  *******");
		
		SospensioniRequest req = new SospensioniRequest();
		req.setId(1);
		req.setDescription("Sbalzo termico");
	
		ResponseEntity<Resp> resp = sospC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("Sospensione aggiornata");
		
	}
	
	public void deleteSospensioni() {
		log.debug("******* delete sospensioni  *******");
		
		ResponseEntity<Resp> resp = sospC.delete(1);
		ResponseEntity<Resp> resp1 = sospC.delete(100);
	
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		assertEquals(HttpStatus.OK, resp1.getStatusCode());
		Resp r = (Resp)resp.getBody();
		Resp r1 = (Resp)resp1.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("Sospensione cancellata");
		
				
	}
	
	public void list() {
		log.debug("Test list sospensioni");
		
		ResponseEntity<?> resp = sospC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<SospensioniDTO> lS = (List<SospensioniDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		lS.forEach(s -> log.debug(s.toString()));
	}
	
}
