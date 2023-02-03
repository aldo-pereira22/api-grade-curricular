package com.aldo.gradecurricular.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MateriaDto {
	
	private Long id;
	
	@NotBlank(message = "Informe o Nome da matéria")
	private String nome;

	@Min(value = 34, message = "Permitido mínimo de 34 horas por matéria.")
	@Max(value = 120, message = "Permitido máximo de 120 horas por matéria.")
	private int horas;
	
	@NotBlank(message = "Informe o Código da matéria")
	private String codigo;
	
	@Min(value = 34, message = "Permitido mínimo de 1 vez ao ano.")
	@Max(value = 120, message = "Permitido máximo de 2 vezess por matéria.")
	private int frequencia;
}
