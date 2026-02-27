package com.betacom.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.services.interfaces.IVeicoliServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/veicoli")
public class VeicoliController {
	
    private final IVeicoliServices veicoliServices;

    @GetMapping("list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;

        try {
            r = veicoliServices.selectAll();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }
	
}
