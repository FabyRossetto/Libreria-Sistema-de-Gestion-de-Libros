/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;


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

   
    //un controlador por cada vista principal
    @GetMapping("")
    public String index() {
        return "PaginaPrincipal.html";
    }

    @GetMapping("/registrarAutor")
    public String GuardarAutor(ModelMap modelo) {
       
        return "PaginaAutor.html";
    }

    @GetMapping("/registrarEditorial")
    public String PaginaEditorial() {
        return "PaginaEditorial.html";
    }

    @GetMapping("/registrarLibro") //me gustaria que vaya a esta pagina cuando tambien quiera modificar,dar de baja,etc. Como lo agrego?
    public String PaginaLibro() {
        return "PaginaLibro.html";
    }

}
