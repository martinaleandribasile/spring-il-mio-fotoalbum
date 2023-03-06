package com.java.foto.apicontroller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.java.foto.model.Foto;

import com.java.foto.repository.FotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/foto")
public class FotoControllerApi {

	@Autowired
	FotoRepository fotoRepository;

	@GetMapping
	public List<Foto> index(@RequestParam(required = false) String title) {
		List<Foto> elencoFoto;
		elencoFoto = fotoRepository.findAll();
		return elencoFoto.stream().filter(Foto::isVisible).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Foto> show(@PathVariable("id") Integer id) {
		Optional<Foto> foto = fotoRepository.findById(id);
		if (foto.isPresent())
			return new ResponseEntity<Foto>(foto.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Foto>(HttpStatus.NOT_FOUND);
	}
	
}