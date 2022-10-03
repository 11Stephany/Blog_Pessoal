package com.example.demo.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.blogpessoal.model.postagem;
import com.example.demo.blogpessoal.repository.PostagemRepository;


@RestController
@RequestMapping ("/postagens")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity <List<postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	@GetMapping("/{id}")
		public ResponseEntity <postagem>getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List <postagem> > getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findByTituloContainingIgnoreCase(titulo));
	}
	@PostMapping
	public ResponseEntity <postagem> post (@Valid @RequestBody postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));		
	}
	@PutMapping
	public ResponseEntity <postagem> put (@Valid @RequestBody postagem postagem){
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem)))
						.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());	
	}
	@ResponseStatus (HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		Optional <postagem> postagem = postagemRepository.findById(id);
			if (postagem.isEmpty())
				throw new ResponseStatusException (HttpStatus.NOT_FOUND);
			postagemRepository.deleteById(id);
	}
}
