/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Fabi
 */

@Entity
public class Libro{
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String id;
    private Long isbn;
    private String titulo;
    private Integer anio;

    private Boolean alta;
    
    @OneToOne //UN LIBRO TIENE UN AUTOR
    private Autor autor;
    @OneToOne //UN LIBRO TIENE UNA EDITORIAL
    private Editorial editorial;

    public Libro() {
    }

    public Libro(String id, Long isbn, String titulo, Integer anio, Boolean alta, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.alta = true;
        this.autor = autor;
        this.editorial = editorial;
    }

   

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the isbn
     */
    public Long getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

//    
    public Boolean getAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {   
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {    
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    public Editorial getEditorial() {   
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "  ******** Libro :" +
                " TITULO=  " + titulo +
                ", id=  " + id +
                ", isbn=  " + isbn +
                ", año=  " + anio +
                ", alta=  " + alta +
                ", autor=  " + autor +
                ", editorial  =" + editorial +
                " ******** ";
    }
    
    
    
}
