/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;

import com.libreria.libreriaSpring.entidades.Autor;
import com.libreria.libreriaSpring.entidades.Editorial;
import com.libreria.libreriaSpring.servicios.LibroServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fabi
 */
@Controller
@RequestMapping
public class LibroController {
    LibroServicio ls=new LibroServicio();
    
    
   
     
     //metodo para crear el libro
     @PostMapping("/registrarLibro")//cuando entra a la barra del servidor ejecuta el metodo
            public String GuardarLibro(ModelMap modelo,@RequestParam Long isbn,@RequestParam String titulo,@RequestParam Integer anio,@RequestParam Autor autor,@RequestParam Editorial edit) throws Exception{
               try{
                ls.CrearLibro(isbn, titulo,anio, autor, edit);
             
               }catch (Exception a) {
                  modelo.put("error",a.getMessage());
                  modelo.put("isbn",isbn);
                  modelo.put("titulo",titulo);
                  modelo.put("anio",anio);
                  modelo.put("autor",autor);
                  modelo.put("editorial",edit);
                   
               }
               
               modelo.put("titulo", "Bienvenido a LA LIBRERIA");
               modelo.put("descripcion","Tu libro fue registrado correctamente");
              return "PaginaPrincipal";  
            }
    
}
