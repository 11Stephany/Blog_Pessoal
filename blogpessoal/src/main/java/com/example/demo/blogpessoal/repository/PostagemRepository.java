package com.example.demo.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.blogpessoal.model.postagem;


@Repository
public interface PostagemRepository extends JpaRepository <postagem, Long> {			
}
