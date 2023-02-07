package com.aldo.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aldo.gradecurricular.dto.MateriaDto;
import com.aldo.gradecurricular.entity.MateriaEntity;
import com.aldo.gradecurricular.exceptions.MateriaException;
import com.aldo.gradecurricular.repository.MateriaRepository;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService {

	
	private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate o suporte";
	private static final String MATERIA_NAO_ENCONTRADA = "Matéria não encontrada";
	
	private MateriaRepository materiaRepository;
	private ModelMapper mapper;
	
	public MateriaService(MateriaRepository materiaRepository) {
		this.mapper = new ModelMapper();
		this.materiaRepository = materiaRepository;
	}
	
	
	@Override
	public Boolean atualizar(MateriaDto materia) {
		try {
		
			this.consultar(materia.getId());
			MateriaEntity materiaEntityAtualizada = this.mapper.map(materia,MateriaEntity.class);

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

//	@Override
//	public MateriaEntity consultar(Long id) {
//		try {
//			Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);
//			if (materiaOptional.isPresent()) {
//				return materiaOptional.get();
//			}
//			throw new MateriaException("Matéria não encontrada", HttpStatus.NOT_FOUND);
//		} catch (MateriaException m) {
//			throw m;
//		} catch (Exception e) {
//			throw new MateriaException("Erro interno identificado. Contate o suporte",
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
	
	@CachePut(key ="#id")
	@Override
	public MateriaDto consultar(Long id) {
		try {
			Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);
			if (materiaOptional.isPresent()) {
				return this.mapper.map(materiaOptional.get(), MateriaDto.class);
			}
			throw new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw new MateriaException(MENSAGEM_ERRO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@CachePut(unless = "#result.size()<3")
	@Override
	public List<MateriaEntity> listar() {
		try {
			return this.materiaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	


	@CacheEvict(allEntries = true)
	@Override
	public Boolean cadastrar(MateriaDto materia) {
		try {

			MateriaEntity materiaEnt = this.mapper.map(materia,MateriaEntity.class);
			this.materiaRepository.save(materiaEnt);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	
	@Override
	public List<MateriaDto> listarPorHorarioMinimo(int horaMinima) {
		
		return this.mapper.map(this.materiaRepository.findByHoraMinima(horaMinima),
				new TypeToken<List<MateriaDto>>() {
				}.getType());
	}
	


}
