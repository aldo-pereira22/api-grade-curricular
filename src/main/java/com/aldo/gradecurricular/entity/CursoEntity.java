package com.aldo.gradecurricular.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_curso")
@Data
@NoArgsConstructor
public class CursoEntity implements Serializable{
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="id")
	private Long id;
	
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name = "nome")
	private String nome;
	

	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name = "codigo")
	private String codigo;
	
	@ManyToAny(fetch = FetchType.LAZY)
	@JoinColumn(name = "materia_id")
	private List<MateriaEntity> materias;

}
