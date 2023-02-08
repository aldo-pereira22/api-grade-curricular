package com.aldo.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aldo.gradecurricular.constante.MensagensConstant;
import com.aldo.gradecurricular.entity.CursoEntity;
import com.aldo.gradecurricular.entity.MateriaEntity;
import com.aldo.gradecurricular.exceptions.CursoException;
import com.aldo.gradecurricular.model.CursoModel;
import com.aldo.gradecurricular.repository.CursoRepository;
import com.aldo.gradecurricular.repository.MateriaRepository;

@CacheConfig(cacheNames = "curso")
@Service
public class CursoService {
	private CursoRepository cursoRepository;

	private MateriaRepository materiaRepository;
	

	@Autowired
	public CursoService(CursoRepository cursoRepository, MateriaRepository materiaRepository) {
		this.cursoRepository = cursoRepository;
		this.materiaRepository = materiaRepository;
	}

	
	public Boolean cadastrar(CursoModel cursoModel) {
		try {
			/*
			 * O id não pode ser informado no cadastro
			 */

			if (cursoModel.getId() != null) {
				throw new CursoException(MensagensConstant.ERRO_ID_INFORMADO.getValor(), HttpStatus.BAD_REQUEST);
			}
			
			/*
			 * Não permite fazer cadastro de cursos com mesmos códigos.
			 */
			if (this.cursoRepository.findCursoByCodigo(cursoModel.getCodCurso()) != null) {
				throw new CursoException(MensagensConstant.ERRO_CURSO_CADASTRADO_ANTERIORMENTE.getValor(), HttpStatus.BAD_REQUEST);
			}
			return this.cadastrarOuAtualizar(cursoModel);

		}catch (CursoException c) {
			throw c;
		}
		catch (Exception e) {
			throw new CursoException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Boolean cadastrarOuAtualizar(CursoModel cursoModel) {
		List<MateriaEntity> listMateriaEntity = new ArrayList<>();

		if (!cursoModel.getMaterias().isEmpty()) {

			cursoModel.getMaterias().forEach(materia -> {
				if (this.materiaRepository.findById(materia).isPresent())
					listMateriaEntity.add(this.materiaRepository.findById(materia).get());
			});
		}

		CursoEntity cursoEntity = new CursoEntity();
		if(cursoModel.getId()!=null) {
			cursoEntity.setId(cursoModel.getId());
		}
		cursoEntity.setCodigo(cursoModel.getCodCurso());
		cursoEntity.setNome(cursoModel.getNome());
		cursoEntity.setMaterias(listMateriaEntity);

		this.cursoRepository.save(cursoEntity);

		return Boolean.TRUE;
	}
	public Boolean atualizar(CursoModel cursoModel) {
		try {
			this.consultarPorCodigo(cursoModel.getCodCurso());
			return this.cadastrarOuAtualizar(cursoModel);
		} catch (CursoException c) {
			throw c;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@CachePut(key = "#codCurso")
	public CursoEntity consultarPorCodigo(String codCurso) {

		try {
			CursoEntity curso = this.cursoRepository.findCursoByCodigo(codCurso);

			if (curso == null) {
				throw new CursoException(MensagensConstant.ERRO_CURSO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
			}
			return curso;

		} catch (CursoException c) {
			throw c;
		} catch (Exception e) {
			throw new CursoException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CachePut(unless = "#result.size()<3")
	public List<CursoEntity> listar() {
		return this.cursoRepository.findAll();
	}
	
	public Boolean excluir(Long cursoId) {
		try {
			if(this.cursoRepository.findById(cursoId).isPresent()) {
				this.cursoRepository.deleteById(cursoId);
				return Boolean.TRUE;
			}
			throw new CursoException(MensagensConstant.ERRO_CURSO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
		}catch (CursoException c) {
			throw c;
		}catch (Exception e) {
			throw new CursoException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
