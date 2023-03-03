package com.java.foto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.foto.model.Foto;


public interface FotoRepository extends JpaRepository< Foto, Integer> {
	public List<Foto> findByTitoloLike(String titoloFoto);
}
