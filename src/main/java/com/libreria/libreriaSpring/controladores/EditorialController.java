/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;


import com.libreria.libreriaSpring.entidades.Editorial;
import com.libreria.libreriaSpring.servicios.EditorialServicio;
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
@RequestMapping("/paginaEditorial")
public class EditorialController {
    @Autowired
    EditorialServicio es;
    
    @GetMapping("/registrarEditorial")
    public String GuardarEditorial() {
        return "PaginaEditorial.html";
    }
     //metodo para crear una editorial
     @PostMapping("/registrarEditorial")//cuando entra a la barra del servidor ejecuta el metodo
            public String GuardarEditorial(ModelMap Modelo,@RequestParam String nombre) throws Exception{
             try{ 
                es.CrearEditorial(nombre);
              Modelo.put("exito","su editorial se ha registrado con exito");
}catch(Exception e){
   Modelo.put("error","hubo un error al registrar la editorial");
}
        return "PaginaEditorial";
    }
    @GetMapping("/editarEditorial")
    public String EditarEditorial() {
        
        return "PaginaEditorial.html";
    } 
            
            @PostMapping("/editarEditorial")
            public String editarEditorial(ModelMap modelo,@RequestParam String nombreViejo,@RequestParam String id,@RequestParam String nombreNuevo)throws Exception{
                try{
                es.modificarEditorial(id, nombreViejo,nombreNuevo);
                modelo.put("exito", "su editorial ha sido editada con exito");
               }catch (Exception a) {
                  modelo.put("error","no se ha podido editar la editorial");
                  
               }
                
              return "PaginaEditorial.html"; 
            }
            @GetMapping("/eliminarEditorial")
    public String Eliminar() {
        
        return "PaginaEditorial.html";
    }
            
            @PostMapping("/eliminarEditorial")
            public String Eliminar(ModelMap modelo,@RequestParam String id)throws Exception{
                try{
                es.darDeBajaEditorial(id);
                modelo.put("exito","su editorial ha sido dada de baja");
             
               }catch (Exception a) {
                  modelo.put("error","su editorial no se pudo dar de baja");
                 
               }
              
              
              return "PaginaEditorial.html";  
            }
            @GetMapping("/buscarEditorialPorid")
            public String buscarEditorialPorId(ModelMap modelo,@RequestParam String id){
                try{
                Editorial edi= es.buscarEditorialPorId(id);
                modelo.put("exito","la editorial encontrada es" + edi);
            }catch (Exception a ){
                 modelo.put("error","no se ha encontrado su editorial");
            }
                return "PaginaEditorial";
  }
            
             @GetMapping("/buscarEditorialPorNombre")
            public String buscarEditorialPorNombre(ModelMap modelo,@RequestParam String nombre){
                try{
                Editorial e =es.buscarEditorialPorNombre(nombre);
                modelo.put("exito","la editorial encontrada es " + e );
            }catch (Exception a ){
                 modelo.put("error","la editorial no se ha podido encontrar");
            }
                return "PaginaEditorial";
  }
            
            
            @GetMapping("/listarEditoriales")
            public String listarEditoriales( ModelMap modelo){
                
               modelo.addAttribute("nombre",es.listarEditorial());
             return "PaginaEditorial";
            } 
           
  }



