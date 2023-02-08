package com.aldo.gradecurricular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldo.gradecurricular.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long>{

}
