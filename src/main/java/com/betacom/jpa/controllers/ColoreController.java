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


import com.betacom.jpa.dto.inputs.ColoreRequest;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.IColoreServices;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/colore/")
public class ColoreController {
	
	private final IColoreServices colS;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() {
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r=colS.list();
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required = true) ColoreRequest req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			colS.create(req);
			r.setMsg("rest_created");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<Resp> update(@RequestBody(required = true)  ColoreRequest req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			colS.update(req);
			r.setMsg("rest_updated");
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
		colS.delete(id);
			r.setMsg("rest_deleted");
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	

}
