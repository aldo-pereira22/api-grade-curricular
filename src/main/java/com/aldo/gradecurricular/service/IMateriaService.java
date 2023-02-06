package com.aldo.gradecurricular.service;

import java.util.List;

import com.aldo.gradecurricular.dto.MateriaDto;
import com.aldo.gradecurricular.entity.MateriaEntity;

public interface IMateriaService {

	public Boolean atualizar(final MateriaDto materia);

	public Boolean exlcuir(final Long id);

	public List<MateriaEntity> listar();

	public MateriaDto consultar(final Long id);

	public Boolean cadastrar(final MateriaDto materia);
}
