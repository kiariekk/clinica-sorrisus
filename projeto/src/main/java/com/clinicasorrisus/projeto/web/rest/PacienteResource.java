package com.clinicasorrisus.projeto.web.rest;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.clinicasorrisus.projeto.service.PacienteService;
import com.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api")
public class PacienteResource {
	
	  private final PacienteService pacienteService;
	  
	 public PacienteResource(PacienteService pacienteService) {
		 this.pacienteService = pacienteService;
	 }
	 
	@PostMapping("/salvar-pacientes")
	@Timed
	public ResponseEntity<PacienteDTO> criar(@Valid @RequestBody PacienteDTO pacienteDTO) throws URISyntaxException{
		
		PacienteDTO savePaciente = pacienteService.save(pacienteDTO);
		return ResponseEntity.created(new URI("/api/salvar-pacientes/" + savePaciente.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert("Paciente", savePaciente.getId().toString()))
	            .body(savePaciente);
	}
	

}
