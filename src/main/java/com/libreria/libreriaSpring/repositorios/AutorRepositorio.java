/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.repositorios;

import com.libreria.libreriaSpring.entidades.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabi
 */
@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{

    
     @Query("SELECT a FROM Autor a WHERE a.nombre= :nombre")
    public Autor buscarAutorPorNombre(@Param("nombre")String nombre);
}
