package com.betacom.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.betacom.jpa.dto.inputs.CategoriaRequest;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.ICategoriaServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("rest/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaServices categoriaServices;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody CategoriaRequest request) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            categoriaServices.create(request);
            r.setMsg("ok");
            return ResponseEntity.ok(r);
        } catch (Exception e) {
            r.setMsg("Errore: " + e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(r);
           
        }
    }
    
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(categoriaServices.findAll());
    }
    @DeleteMapping("delete/{id}")
	public ResponseEntity<Resp> delete(@PathVariable Integer id) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			categoriaServices.delete(id);
			r.setMsg("Categoria cancellata correttamente");
		} catch (Exception e) {
			r.setMsg("Errore nella cancellazione: " + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
}