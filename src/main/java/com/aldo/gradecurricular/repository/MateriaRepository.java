package com.aldo.gradecurricular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aldo.gradecurricular.entity.MateriaEntity;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
	@Query("select m from MateriaEntity m where m.horas <= :horaMinima")
	public List<MateriaEntity> findByHoraMinima(@Param("horaMinima") int horaMinima);
}
