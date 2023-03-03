package com.java.foto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.foto.model.Tag;

public interface TagRepository extends JpaRepository< Tag, Integer> {

}
