package com.kairos.services;

import java.util.List;

import com.kairos.model.Persona;

public interface IPersonaService {
	
	public List listAll();
	public Persona addPersona(Persona p) ;
	public Persona updatePersona(Persona persona);
	public boolean deletePersona(Long id);	
	public Persona findPersona(Long id) ;
	
}
