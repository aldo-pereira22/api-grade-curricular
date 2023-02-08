package com.aldo.gradecurricular.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CursoModel {
	
	@NotBlank(message = "Nome deve ser preenchido")
	@Size(min = 10, max = 30)
	private String nome;
	
	@NotBlank(message = "Código deve ser preenchido")
	private String codCurso;
	private List<Long> materias;
}
