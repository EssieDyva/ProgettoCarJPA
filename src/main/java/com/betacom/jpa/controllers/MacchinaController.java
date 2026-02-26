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

import com.betacom.jpa.dto.inputs.MacchinaRequest;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.IMacchinaServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/macchina/")
public class MacchinaController {

    private final IMacchinaServices macchinaServices;

    @GetMapping("list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;

        try {
            r = macchinaServices.list();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }

    @PostMapping("create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) MacchinaRequest request) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            macchinaServices.create(request);
            r.setMsg("Macchina creata");
        } catch (Exception e) {
            r.setMsg("Errore nella creazione " + e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) MacchinaRequest request) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            macchinaServices.update(request);
            r.setMsg("Macchina aggiornata");
        } catch (Exception e) {
            r.setMsg("Errore nella creazione " + e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Resp> delete(@PathVariable Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            macchinaServices.delete(id);
            r.setMsg("Macchina cancellata");
        } catch (Exception e) {
            r.setMsg("Errore nella cancellazione " + e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(r);
    }
}