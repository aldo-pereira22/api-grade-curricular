package com.aldo.gradecurricular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.aldo.gradecurricular.dto.MateriaDto;
import com.aldo.gradecurricular.entity.MateriaEntity;
import com.aldo.gradecurricular.service.MateriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private MateriaService materiaService;

	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MateriaDto> consultaMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultar(id));
	}

	@PostMapping
	private ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.cadastrar(materia));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.exlcuir(id));
	}

	@PutMapping
	public ResponseEntity<Boolean> atualizarMateria(@Valid @RequestBody MateriaDto materia) {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));

	}

}
