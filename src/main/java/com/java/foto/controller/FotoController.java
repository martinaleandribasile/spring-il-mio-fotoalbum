package com.java.foto.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.foto.model.Categoria;
import com.java.foto.model.Foto;
import com.java.foto.repository.CategoriaRepository;
import com.java.foto.repository.FotoRepository;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/foto")  

public class FotoController {

	@Autowired
	FotoRepository fotoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public String index(@RequestParam(name="keyword", required= false) String keyword,  Model model) {
		List<Foto> elencoFoto;
		boolean ricerca = false;
		String valueInput="";
		if(keyword!=null && !keyword.isEmpty()) {
			elencoFoto= fotoRepository.findByTitoloLike("%"+ keyword +"%");
			 ricerca=true;
			 valueInput=keyword;
		}else {
			elencoFoto= fotoRepository.findAll();
		}
		model.addAttribute("elencoFoto" , elencoFoto);
		model.addAttribute("ricerca", ricerca);
		model.addAttribute("value", valueInput);
		return "foto/indexFoto";
	}
	
	@GetMapping("/{id}") 
	public String dettailsFoto(@PathVariable("id") Integer id, Model model) {
		Optional<Foto> p= fotoRepository.findById(id);
		if(p.isEmpty()) {
			return "redirect:/error";
		}
		model.addAttribute("today", LocalDate.now());
		model.addAttribute("foto",p.get());
		return "foto/dettaglioFoto";
	}
	
	
	@GetMapping("/newFoto")
	public String create(Model model) {
		Foto foto=new Foto();
		List<Categoria> categorie = categoriaRepository.findAll();
		model.addAttribute("categorie", categorie);
		model.addAttribute("foto", foto);
		return "foto/newFoto";
	}
	
	@PostMapping("/newFoto")
	public String store(@Valid @ModelAttribute("foto") Foto formFoto,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<Categoria> categorie = categoriaRepository.findAll();
			model.addAttribute("categorie", categorie);
			return "foto/newFoto";
		}
		fotoRepository.save(formFoto);
		return "redirect:/foto";
		
	}
	
	@GetMapping("/editFoto/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Foto> p= fotoRepository.findById(id);
		if(p.isEmpty()) {
			return "redirect:/error";
		}
		List<Categoria> categorie = categoriaRepository.findAll();
		model.addAttribute("categorie", categorie);
		model.addAttribute("foto",p.get());
		return "foto/editFoto";
	}
	@PostMapping("/editFoto/{id}")
	public String update( @PathVariable("id") Integer id,@Valid @ModelAttribute("foto") Foto formFoto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "foto/editFoto";
		}
		fotoRepository.save(formFoto);
		return "redirect:/foto/"+ id;
	}
	
	@PostMapping("/deleteFoto/{id}")
	public String delete(@PathVariable("id") Integer id) {
		fotoRepository.deleteById(id);
		return "redirect:/foto";
	}
}
