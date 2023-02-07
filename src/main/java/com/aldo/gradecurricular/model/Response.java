package com.aldo.gradecurricular.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.aldo.gradecurricular.entity.MateriaEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T> extends RepresentationModel<Response<T>> {
	private int statusCode;
	private T data;
	private long timeStamp;
	
	
	public Response(){
		this.timeStamp = System.currentTimeMillis();
	}
}
