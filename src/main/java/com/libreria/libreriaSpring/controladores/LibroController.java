/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.controladores;

import com.libreria.libreriaSpring.entidades.Autor;
import com.libreria.libreriaSpring.entidades.Editorial;
import com.libreria.libreriaSpring.entidades.Libro;
import com.libreria.libreriaSpring.servicios.LibroServicio;
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
@RequestMapping("/PagLibro")
public class LibroController {

    @Autowired
    private LibroServicio ls;

    @GetMapping("/registrarLibro")
    public String GuardarLibro(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }

    //metodo para crear el libro
    @PostMapping("/registrarLibro")//cuando entra a la barra del servidor ejecuta el metodo
    public String GuardarLibro(ModelMap modelo, @RequestParam String titulo, @RequestParam Long isbn, @RequestParam Integer anio, @RequestParam String nombreAutor, @RequestParam String nombre) throws Exception {
        try {
            ls.CrearLibro(titulo, isbn, anio, nombreAutor, nombre);
            modelo.put("exito", "su libro se ha registrado con exito");
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        } catch (Exception a) {
            modelo.put("error", "no se pudo registrar su libro");
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        }

        return "PaginaLibro";
    }

    @GetMapping("/editarLibro")
    public String editarLibro(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }

    @PostMapping("/editarLibro")
    public String editarLibro(ModelMap modelo, @RequestParam String id, @RequestParam Long isbn, @RequestParam String tituloNuevo, @RequestParam Integer anio, @RequestParam String autorViejo, @RequestParam String autorNuevo, @RequestParam String nombreViejo, @RequestParam String nombreNuevo) throws Exception {
        try {
            ls.modificarLibro(id, isbn, tituloNuevo, anio, autorViejo, autorNuevo, nombreViejo, nombreNuevo);
            modelo.put("exito", "su libro se ha editado con exito");
            modelo.addAttribute("nombreLibros", ls.listarLibro());
            
        } catch (Exception a) {
            modelo.put("error", "su libro no se ha podido editar");
            modelo.addAttribute("nombreLibros", ls.listarLibro());   
        }

        return "PaginaLibro";
    }

    @GetMapping("/eliminarLibro")
    public String Eliminar(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }

    @PostMapping("/eliminarLibro")
    public String Eliminar(ModelMap modelo, @RequestParam String id) throws Exception {
        try {
            ls.EliminarLibro(id);
            modelo.put("exito", "su libro se ha eliminado con exito");
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        } catch (Exception a) {
            modelo.put("error", "su libro no se ha podido eliminar");
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        }

        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorid")
    public String buscarLibroPorId(ModelMap modelo, @RequestParam String id) {
        try {
            Libro a = ls.buscarLibroPorId(id);
            modelo.put("exito", "El libro encontrado es " + a);
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        } catch (Exception a) {
            modelo.put("error", "su libro no se ha podido encontrar");
            modelo.addAttribute("nombreLibros", ls.listarLibro());
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorNombre")
    public String buscarLibroPorNombre(ModelMap modelo, @RequestParam String titulo) {
        try {
            Libro li = ls.buscarLibroPorTitulo(titulo);
            if (li != null) {

                modelo.put("exito", "El libro encontrado es: " + li);
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
            if (li == null) {
                modelo.put("error", "el libro no se ha podido encontrar");
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
        } catch (Exception a) {
            return null;
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorIsbn")
    public String buscarLibroPorIsbn(ModelMap modelo, @RequestParam Long isbn) {
        try {
            List<Libro> li = ls.buscarLibroPorIsbn(isbn);
            if (li == null|| li.isEmpty()) {
                modelo.put("error", "El libro no se ha podido encontrar");
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
            else {
                modelo.put("exito", "el libro encontrado es : " + li);
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
            
        } catch (Exception a) {
            return null;
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorAutor")
    public String buscarLibroPorAutor(ModelMap modelo, @RequestParam String autor) {
        try {
            List<Libro> li = ls.buscarLibroPorAutor(autor);
            if (li == null || li.isEmpty()) {
                modelo.put("error", "El libro no se ha podido encontrar");
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
            else {
                modelo.put("exito", li);
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
            
        } catch (Exception a) {
            return null;
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorEditorial")
    public String buscarLibroPorEditorial(ModelMap modelo, @RequestParam String editorial) {
        try {
            List<Libro> li = ls.buscarLibroPorEditorial(editorial);
             if (li == null || li.isEmpty()) {
                modelo.put("error", "El libro no se ha podido encontrar");
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
            else {
                modelo.put("exito", li);
                modelo.addAttribute("nombreLibros", ls.listarLibro());
            }
           
        } catch (Exception a) {
            return null;
        }
        return "PaginaLibro.html";
    }

}
