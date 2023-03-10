package com.java.foto.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.foto.model.Categoria;
import com.java.foto.repository.CategoriaRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/categorie")
public class CategoriaController {
	@Autowired
	private CategoriaRepository catrep;
	
	@GetMapping()
	public String index(			
			Model model) {	
		List<Categoria> res = catrep.findAll();
		model.addAttribute("categorie", res);
		return "indexCat";
	}

	@GetMapping("/create")	
	public String create(Model model) {
		Categoria categoria =new Categoria();	

		model.addAttribute("categoria", categoria);

		return "createCat";
	}

	@PostMapping("/create") 
	public String store(
		@Valid @ModelAttribute("categorie") Categoria formCategoria, 
		BindingResult bindingResult,
		Model model){

		if (bindingResult.hasErrors())
			return "createCat";

		catrep.save(formCategoria);

		return "redirect:/categorie"; 

	}
	
	@GetMapping("/edit/{id}") 
	public String edit(@PathVariable("id") Integer id,Model model ) {
		
		Categoria c;
		c = catrep.getReferenceById(id);
		model.addAttribute("categoria", c);
		
	
		return "editCat";
	}
	
	@PostMapping("/edit/{id}")
	public String update(
			@PathVariable("id") Integer id,@Valid @ModelAttribute("foto") Categoria formCategoria, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "editCat";
		
		catrep.save(formCategoria);
		
		return "redirect:/categorie";
		
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

	   catrep.deleteById(id);

	   return "redirect:/categorie";
	}


}
