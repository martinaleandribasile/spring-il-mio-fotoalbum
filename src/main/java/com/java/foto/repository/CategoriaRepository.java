package com.java.foto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.foto.model.Categoria;

public interface CategoriaRepository extends JpaRepository< Categoria, Integer> {

}
