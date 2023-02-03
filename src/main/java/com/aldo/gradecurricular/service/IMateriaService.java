package com.aldo.gradecurricular.service;

import java.util.List;

import com.aldo.gradecurricular.entity.MateriaEntity;

public interface IMateriaService {

	public Boolean atualizar(final MateriaEntity materia);

	public Boolean exlcuir(final Long id);

	public List<MateriaEntity> listar();

	public MateriaEntity consultar(final Long id);

	public Boolean cadastrar(final MateriaEntity materia);
}
