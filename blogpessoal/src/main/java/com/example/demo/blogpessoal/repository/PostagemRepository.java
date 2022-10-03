package com.example.demo.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.blogpessoal.model.postagem;


@Repository
public interface PostagemRepository extends JpaRepository <postagem, Long> {
	public List <postagem> findByTituloContainingIgnoreCase(@Param("titulo")String titulo);	
}

