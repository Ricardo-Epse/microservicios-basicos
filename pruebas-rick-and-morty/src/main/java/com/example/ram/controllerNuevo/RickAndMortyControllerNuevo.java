package com.example.ram.controllerNuevo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//http://localhost:8083/v3/api-docs
//http://localhost:8083/swagger-ui/index.html

@RestController
@RequestMapping("/api/v1/rickandmorty/personajes")
public class RickAndMortyControllerNuevo{
	
	private Faker faker = new Faker();
	
	private List<String> characters = new ArrayList<>();
	
	
	private static final Logger log = LoggerFactory.getLogger(RickAndMortyControllerNuevo.class);

	@PostConstruct
	public void init() {	
		for (int i = 0; i < 10; i++) {		
			characters.add(faker.rickAndMorty().character());	
		}	
	}
	
	@GetMapping
	@Operation(summary = "Get list of Rick and morty")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the characters", 
	    content =  { @Content(mediaType = "application/json", array =@ArraySchema(schema=@Schema(implementation = String.class))) }),
	  @ApiResponse(responseCode = "404", description = "Characters not found", 
	    content = @Content) })
	public ResponseEntity<List<String>> getCharacters(){
		
		log.info("Rick and morty");
		return ResponseEntity.ok(characters);
	}
	
	

}
