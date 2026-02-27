package com.betacom.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.inputs.BiciRequest;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.IBiciServices;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/bici")
public class BiciController {
	
	private final IBiciServices bicS;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() {
		Object r;
		HttpStatus status = HttpStatus.OK;
		try {
			r=bicS.list();
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required = true) BiciRequest req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			bicS.create(req);
			r.setMsg("Bici creata");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<Resp> update(@RequestBody(required = true)  BiciRequest req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			bicS.update(req);
			r.setMsg("Bici aggiornata");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Resp> delete(@PathVariable(required = true)  Integer id){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
		bicS.delete(id);
			r.setMsg("Bici cancellata");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
}
