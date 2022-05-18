/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;

import com.libreria.libreriaSpring.servicios.AutorServicio;
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
@RequestMapping("")
public class ListaControlador {
    @Autowired
    private AutorServicio as;
   @GetMapping("/listarAutores")
    public String listarAutores(ModelMap modelo) {
        
        modelo.addAttribute("nombreAutores", as.listarAutor());
      
        return "lista.html";
    }
}
