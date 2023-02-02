package com.aldo.gradecurricular.service;

import com.aldo.gradecurricular.entity.MateriaEntity;

public interface IMateriaService {
	
	public Boolean atualizar(final MateriaEntity materia); 
		
	public Boolean exlcuir(final Long id);
}
