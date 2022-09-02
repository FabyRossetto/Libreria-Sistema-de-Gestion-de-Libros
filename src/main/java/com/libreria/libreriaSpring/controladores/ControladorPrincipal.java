/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;


import com.libreria.libreriaSpring.servicios.AutorServicio;
import com.libreria.libreriaSpring.servicios.EditorialServicio;
import com.libreria.libreriaSpring.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fabi
 */
@Controller
@RequestMapping("")//lee a partir de la barra
public class ControladorPrincipal {
@Autowired
    private AutorServicio as;

 @Autowired
    private EditorialServicio es;
 @Autowired
 private LibroServicio ls;
   
    //un controlador por cada vista principal
    @GetMapping("")
    public String index() {
        return "PaginaPrincipal.html";
    }

    @GetMapping("/registrarAutor")
    public String GuardarAutor(ModelMap modelo) {
        modelo.addAttribute("nombreAutores", as.listarAutor());
        return "PaginaAutor.html";
    }
    
    
    @GetMapping("/registrarEditorial")
    public String PaginaEditorial(ModelMap modelo) {
         modelo.addAttribute("nombre",es.listarEditorial());
        return "PaginaEditorial.html";
    }
    
           

    @GetMapping("/registrarLibro") 
    public String PaginaLibro(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }
    
}
