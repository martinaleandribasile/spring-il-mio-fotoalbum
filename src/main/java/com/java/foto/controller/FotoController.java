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

import com.java.foto.model.Foto;
import com.java.foto.repository.FotoRepository;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/foto")  

public class FotoController {

	@Autowired
	FotoRepository fotoRepository;
	
	
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
	
	@GetMapping("{id}") 
	public String dettailsPizza(@PathVariable("id") Integer id, Model model) {
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
		Foto pizza=new Foto();
		
		model.addAttribute("foto", pizza);
		
		return "foto/newFoto";
	}
	
	@PostMapping("/newFoto")
	public String store(@Valid @ModelAttribute("foto") Foto formFoto,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
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
		model.addAttribute("foto",p.get());
		return "foto/editFoto";
	}
	@PostMapping("/editFoto/{id}")
	public String update( @PathVariable("id") Integer id,@Valid @ModelAttribute("pizza") Foto formFoto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "pizze/editPizza";
		}
		fotoRepository.save(formFoto);
		return "redirect:/pizze/"+ id;
	}
	
	@PostMapping("/deletePizza/{id}")
	public String delete(@PathVariable("id") Integer id) {
		fotoRepository.deleteById(id);
		return "redirect:/pizze";
	}
}
