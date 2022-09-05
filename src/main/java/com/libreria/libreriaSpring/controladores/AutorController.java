/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;

import com.libreria.libreriaSpring.entidades.Autor;
import com.libreria.libreriaSpring.servicios.AutorServicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fabi
 */
@Controller
@RequestMapping("/paginaDelAutor")
public class AutorController {

    @Autowired
    private AutorServicio as;

    
    //metodo para crear el autor
    @PostMapping("/registrarAutor")//cuando entra a la barra del servidor ejecuta el metodo
    public String GuardarAutor(ModelMap Modelo,@RequestParam String nombre) throws Exception {
try{
        as.CrearAutor(nombre);
        Modelo.put("exito","el autor ya forma parte de la base de datos");
        
        Modelo.addAttribute("nombreAutores", as.listarAutor());
}catch(Exception e){
   Modelo.put("error","hubo un error al registrar el autor");

}
        return "PaginaAutor";
    }
    
     
    
    @PostMapping("/editarAutor")
    public String editarAutor(ModelMap modelo, @RequestParam String nombreViejo,  @RequestParam String nombreNuevo) throws Exception {
        try {
            as.modificarAutor( nombreViejo,nombreNuevo);
           modelo.put("exito","su autor se ha editado con exito");
           modelo.addAttribute("nombreAutores", as.listarAutor());
        } catch (Exception a) {
            modelo.put("error", "su autor no se ha podido editar");
           
        }

       
        return "PaginaAutor";
    }


    @PostMapping("/eliminarAutor")
    public String Eliminar(ModelMap modelo, @RequestParam String id) throws Exception {
        try {
            as.darDeBajaAutor(id);
            modelo.put("exito","su autor se ha eliminado con exito");
            modelo.addAttribute("nombreAutores", as.listarAutor());

        } catch (Exception a) {
            modelo.put("error", "su autor no se ha podido eliminar, si su autor se encuentra vinculado a un libro,primero debe eliminar el libro");

        }

       
        return "PaginaAutor.html";
    }

    @GetMapping("/buscarAutorPorid")
    public String buscarAutorPorId(ModelMap modelo, @RequestParam String id) {
        try {
            Autor a = as.buscarAutorPorId(id);
            modelo.put("exito","el autor encontrado es "+ a);
        } catch (Exception a) {
            modelo.put("error", "su autor no se ha podido encontrar");
        }
        return "PaginaAutor.html";
    }

    @GetMapping("/buscarAutorPorNombre")
    public String buscarAutorPorNombre(ModelMap modelo,@RequestParam String nombre) {
        try {
           Autor au=as.buscarAutorPorNombre(nombre);
            modelo.put("exito","el autor encontrado es " + au );
        } catch (Exception a) {
            modelo.put("error", "el autor no se ha podido encontrar");
        }
        return "PaginaAutor.html";
    }
    
     

   

}
