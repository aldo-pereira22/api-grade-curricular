package com.aldo.gradecurricular.service;

import java.util.List;

import com.aldo.gradecurricular.entity.CursoEntity;
import com.aldo.gradecurricular.model.CursoModel;

public interface ICursoService {
	Boolean cadastrar(CursoModel cursoModel);
	
	Boolean atualizar(CursoModel cursoModel);
	
	Boolean excluir(Long cursoId);
	
	CursoEntity consultarPorCodigo(String codCurso);
	
	List<CursoEntity> listar();
}
