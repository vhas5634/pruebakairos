package com.kairos.services;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairos.controller.PersonaController;
import com.kairos.model.Persona;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonaControllerTest {
	private Persona p1;
	private Persona p2;
	private Persona p3;
	private List<Persona> personas;
	
	 @Mock
	 IPersonaService personaService;

	 private MockMvc mockMvc;
	
	
	@Before
    public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		p1 = new Persona("Juan Lopez",'H',sdf.parse("1975-05-20"),"Mexicano","5566331122","jlopez@gmail.com");
		p2 = new Persona("Carlos Martinez",'H',sdf.parse("1985-05-20"),"Mexicano","5566531122","cmartinez@gmail.com");
		p3 = new Persona("Maria Lara",'M',sdf.parse("1995-12-20"),"Mexicana","5566066112","mlara@gmail.com");
		personas=new ArrayList<Persona>();
		personas.add(p1);
		personas.add(p2);
		personas.add(p3);
		
		MockitoAnnotations.initMocks(this);
        PersonaController controller = new PersonaController(personaService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
	


	@Test
	public void addPersonaTest() throws Exception {	
		mockMvc.perform(post("/persona/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(p1)))
				.andExpect(status().isCreated());
		mockMvc.perform(post("/persona/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(p2)))
		.andExpect(status().isCreated());
		mockMvc.perform(post("/persona/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(p3)))
		.andExpect(status().isCreated());
	}


	
	@Test
	public void getPersonaByIdTest() throws Exception {		
		mockMvc.perform(get("/persona/get/2"))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void updatePersonaTest() throws Exception {		
		p1.setId(1L);
		p1.setEmail("otrocorreo@gmail.com");
		mockMvc.perform(put("/persona/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(p1)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deletePersonaTest() throws Exception {		
		mockMvc.perform(delete("/persona/delete/1"))
				.andExpect(status().isOk());
	}
	
	


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
