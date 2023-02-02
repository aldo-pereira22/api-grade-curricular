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

import com.aldo.gradecurricular.entity.MateriaEntity;
import com.aldo.gradecurricular.repository.MateriaRepository;
import com.aldo.gradecurricular.service.MateriaService;

@RestController
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private MateriaService materiaService;
	
	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias(){
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MateriaEntity> buscaMateria(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findById(id).get());
	}
	
	@PostMapping
	private ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia){
		try {
			this.materiaRepository.save(materia);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);	
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id){
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.exlcuir(id));

		
	}
	
	@PutMapping
	public ResponseEntity<Boolean> atualizarMateria(@RequestBody MateriaEntity materia) {
			
	
			
			return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));

	}
		
			
}
