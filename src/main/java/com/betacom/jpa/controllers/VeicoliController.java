package com.betacom.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.services.interfaces.IVeicoliServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/veicoli")
public class VeicoliController {
	
    private final IVeicoliServices veicoliServices;

    @GetMapping("listByFilter")
    public ResponseEntity<Object> listByFilter(@RequestParam(required = false) Integer id, 
            @RequestParam(required = false) String colore, 
            @RequestParam(required = false) String categoria, 
            @RequestParam(required = false) Integer annoProduzione) {
        Object r;
        HttpStatus status = HttpStatus.OK;

        try {
            r = veicoliServices.selectByFilter(id, colore, categoria, annoProduzione);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("listByTarga")
    public ResponseEntity<Object> listByTarga(
            @RequestParam(required = true) String targa) {
        Object r;
        HttpStatus status = HttpStatus.OK;

        try {
            r = veicoliServices.selectByTarga(targa);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }
	
    @GetMapping("list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;

        try {
            r = veicoliServices.list();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }
}
