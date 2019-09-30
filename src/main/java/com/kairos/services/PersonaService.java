package com.kairos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kairos.model.Persona;
import com.kairos.repositories.PersonaRepository;

@Service
public class PersonaService implements IPersonaService{

	@Autowired
	private PersonaRepository repo;

	public List listAll() {
		Iterable personas = repo.findAll();
		List lista = new ArrayList();
		personas.forEach(lista::add);
		return lista;

	}

	public Persona addPersona(Persona p) {
		return repo.save(p);

	}

	public Persona updatePersona(Persona persona) {
		if (persona.getId() != null && repo.existsById(persona.getId()))
			return repo.save(persona);

		return null;
	}

	public boolean deletePersona(Long id) {
		if (id != null && repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	public Persona findPersona(Long id) {
		if (id != null && repo.existsById(id)) {
			return repo.findById(id).get();
			
		}
		return null;
	}

}
