package com.java.foto.apicontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.foto.model.Comment;
import com.java.foto.model.Foto;
import com.java.foto.repository.CommentRepository;
import com.java.foto.repository.FotoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentControllerApi {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired 
	private FotoRepository fotoRepository;
	
	@PostMapping("{id}")
	public Comment create(@PathVariable("id") Integer id, @Valid @RequestBody Comment comment) {
		Foto foto = fotoRepository.getReferenceById(id);
		comment.setFoto(foto);
		return commentRepository.save(comment);
		
	}
}
