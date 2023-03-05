package com.java.foto.model;


import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table()
public class Foto {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Id               // INDICA LA NOSTRA CHIAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@NotNull(message="Titolo: campo obbligatorio")
	@NotEmpty(message = "Titolo: campo obbligatorio")
	@Size(min=3, max=100, message="il Titolo deve avere tra i 3 e i 100 caratteri")
//	@Column(columnDefinition = "CHAR(100)")
	private String titolo;
	@Size(min=3, max=1000, message="la descrizione deve avere tra i 3 e i 100 caratteri")
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	@NotNull(message="URL: campo obbligatorio")
	@NotEmpty(message = "URL: campo obbligatorio")
	@Column(columnDefinition = "TEXT")
	private String url;
	boolean visible;
	@NotNull(message="Tag: campo obbligatorio")
	@NotEmpty(message = "Tag: campo obbligatorio")
	@Size(min=3, max=50, message="il Tag deve avere tra i 3 e i 50 caratteri")
//	@Column(columnDefinition = "CHAR(100)")
	private String tag;
	@ManyToMany()
	@JoinTable(name = "foto_categoria")
	private List<Categoria> categoria;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public List<Categoria> getCategoria() {
		return categoria;
	}
	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
