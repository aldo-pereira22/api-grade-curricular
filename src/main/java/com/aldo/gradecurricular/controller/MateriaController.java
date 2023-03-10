package com.aldo.gradecurricular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import com.aldo.gradecurricular.model.Response;
import com.aldo.gradecurricular.service.MateriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/materia")
public class MateriaController {
	
	private static final String DELETE = "DELETE";
	private static final String UPDATE = "UPDATE";

	@Autowired
	private MateriaService materiaService;

	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listar());
	}

//	@GetMapping
//	public ResponseEntity<Response<List<MateriaDto>>> listarMaterias() {
//		Response<List<MateriaDto>> response = new Response<>();
//		response.setData(this.materiaService.listar());
//		response.setStatusCode(HttpStatus.OK.value());
//		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
//				.withSelfRel());
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}

	
//	@GetMapping("/{id}")
//	public ResponseEntity<MateriaDto> consultaMateria(@PathVariable Long id) {
//		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultar(id));
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<MateriaDto>> consultaMateria(@PathVariable Long id) {
		Response<MateriaDto> response = new Response<>();
		MateriaDto materia = this.materiaService.consultar(id);
		response.setData(materia);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateria(id))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).excluirMateria(id))
				.withRel(DELETE));
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(materia))
				.withRel(UPDATE));
		return ResponseEntity.status(HttpStatus.OK).body(response);
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
	

	@GetMapping("/horario-minimo/{horaMinima}")
	public ResponseEntity<Response<List<MateriaDto>>> consultaMateriaPorHoraMinima(@PathVariable int horaMinima) {
		Response<List<MateriaDto>> response = new Response<>();
		List<MateriaDto> materia = this.materiaService.listarPorHorarioMinimo(horaMinima);
		response.setData(materia);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultaMateriaPorHoraMinima(horaMinima))
				.withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
