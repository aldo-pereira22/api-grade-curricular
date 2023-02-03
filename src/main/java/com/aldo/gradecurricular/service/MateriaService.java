package com.aldo.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aldo.gradecurricular.dto.MateriaDto;
import com.aldo.gradecurricular.entity.MateriaEntity;
import com.aldo.gradecurricular.exceptions.MateriaException;
import com.aldo.gradecurricular.repository.MateriaRepository;

@Service
public class MateriaService implements IMateriaService {

	@Autowired
	MateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaDto materia) {
		try {
			ModelMapper mapper = new ModelMapper();
			this.consultar(materia.getId());

			MateriaEntity materiaEntityAtualizada = mapper.map(materia,MateriaEntity.class);

			this.materiaRepository.save(materiaEntityAtualizada);

			return Boolean.TRUE;

		}catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean exlcuir(Long id) {
		try {
			this.consultar(id);
			this.materiaRepository.deleteById(id);
			return true;
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {

			throw e;

		}
	}

	@Override
	public MateriaEntity consultar(Long id) {
		try {
			Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);
			if (materiaOptional.isPresent()) {
				return materiaOptional.get();
			}
			throw new MateriaException("Matéria não encontrada", HttpStatus.NOT_FOUND);
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw new MateriaException("Erro interno identificado. Contate o suporte",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public List<MateriaEntity> listar() {
		try {
			return this.materiaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}


	@Override
	public Boolean cadastrar(MateriaDto materia) {
		try {
			ModelMapper mapper = new ModelMapper();
			MateriaEntity materiaEnt = mapper.map(materia,MateriaEntity.class);
			this.materiaRepository.save(materiaEnt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
