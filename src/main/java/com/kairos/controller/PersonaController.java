package com.kairos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.model.Persona;
import com.kairos.services.IPersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	private IPersonaService personaService;

	@Autowired
	public PersonaController(IPersonaService personaService) {
		this.personaService = personaService;
	}

	@GetMapping("/listAll")
	public ResponseEntity ListarPersonas() {
		return ResponseEntity.ok(this.personaService.listAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity findPersonas(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(this.personaService.findPersona(id));
	}

	@PostMapping("/add")
	public ResponseEntity addPersonas(@Valid @RequestBody Persona persona) {
		Persona personaCreated = this.personaService.addPersona(persona);
		return new ResponseEntity(personaCreated, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity UpdatePersonas(@Valid @RequestBody Persona persona) {
		Persona personaUpdate = this.personaService.updatePersona(persona);
		return new ResponseEntity(personaUpdate, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deletePersonas(@Valid @PathVariable("id") Long id) {

		boolean borrado = this.personaService.deletePersona(id);
		System.out.println(borrado);
		if (borrado)
			return ResponseEntity.ok(true);
		else
			return new ResponseEntity(false, HttpStatus.NOT_MODIFIED);

	}

}
