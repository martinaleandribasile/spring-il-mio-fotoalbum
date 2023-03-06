package com.java.foto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.foto.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
