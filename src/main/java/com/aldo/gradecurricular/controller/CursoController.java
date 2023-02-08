package com.aldo.gradecurricular.controller;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldo.gradecurricular.model.CursoModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {

	@PostMapping
	public ResponseEntity<Void> cadastrarCurso(@Valid @RequestBody CursoModel curso){
		throw new NotYetImplementedException();
	}
}
