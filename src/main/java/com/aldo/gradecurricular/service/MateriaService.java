package com.aldo.gradecurricular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldo.gradecurricular.entity.MateriaEntity;
import com.aldo.gradecurricular.repository.MateriaRepository;


@Service
public class MateriaService implements IMateriaService{

	@Autowired MateriaRepository materiaRepository;
	
	
	@Override
	public Boolean atualizar(MateriaEntity materia) {
		try {
			//buscamos pela materia que gostaríamos de atualizar
			MateriaEntity materiaEntityAtualizada = this.materiaRepository.findById(materia.getId()).get();
			
			//atualizamos todos os valores
			materiaEntityAtualizada.setNome(materia.getNome());
			materiaEntityAtualizada.setCodigo(materia.getCodigo());
			materiaEntityAtualizada.setHoras(materia.getHoras());
			materiaEntityAtualizada.setNome(materia.getNome());
			materiaEntityAtualizada.setFrequencia(materia.getFrequencia());
			
			//salvamos as alteracoes
			this.materiaRepository.save(materiaEntityAtualizada);
			
			return true;	
			
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean exlcuir(Long id) {
		try {
			this.materiaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			
			return false;
			
		}
	}

}
