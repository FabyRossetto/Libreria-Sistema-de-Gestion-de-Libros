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
@RequestMapping("/pagLibro")
public class LibroController {

    @Autowired
    private LibroServicio ls;

    @GetMapping("/pagLibro")
    public String PaginaLibro(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }

    @GetMapping("/registrarLibro")
    public String GuardarLibro(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }

    //metodo para crear el libro
    @PostMapping("/registrarLibro")//cuando entra a la barra del servidor ejecuta el metodo
    public String GuardarLibro(ModelMap modelo, @RequestParam String titulo, @RequestParam Long isbn, @RequestParam Integer anio, @RequestParam String autor, @RequestParam String edit) throws Exception {
        try {
            ls.CrearLibro(titulo, isbn, anio, autor, edit);
            modelo.put("exito", "su libro se ha registrado con exito");
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        } catch (Exception a) {
            modelo.put("error", "no se pudo registrar su libro");

        }

        return "PaginaLibro";
    }

    @GetMapping("/editarLibro")
    public String editarLibro(ModelMap modelo) {
        modelo.addAttribute("nombreLibros", ls.listarLibro());
        return "PaginaLibro.html";
    }

    @PostMapping("/editarLibro")
    public String editarLibro(ModelMap modelo, @RequestParam String id, @RequestParam Long isbn, @RequestParam String tituloViejo, @RequestParam String tituloNuevo, @RequestParam Integer anio, @RequestParam String idAutor, @RequestParam String autorViejo, @RequestParam String autorNuevo, @RequestParam String idEditorial, @RequestParam String editVieja, @RequestParam String editNueva) throws Exception {
        try {
            ls.modificarLibro(id, isbn, tituloViejo, tituloNuevo, anio, idAutor, autorViejo, autorNuevo, idEditorial, editVieja, editNueva);
            modelo.put("exito", "su libro se ha editado con exito");
            modelo.addAttribute("nombreLibros", ls.listarLibro());
        } catch (Exception a) {
            modelo.put("error", "su libro no se ha podido editar");

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
            ls.darDeBajaLibro(id);
            modelo.put("exito", "su libro se ha eliminado con exito");
            modelo.addAttribute("nombreLibros", ls.listarLibro());

        } catch (Exception a) {
            modelo.put("error", "su libro no se ha podido eliminar");

        }

        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorid")
    public String buscarLibroPorId(ModelMap modelo, @RequestParam String id) {
        try {
            Libro a = ls.buscarLibroPorId(id);
            modelo.put("exito", "el libro encontrado es " + a);
        } catch (Exception a) {
            modelo.put("error", "su libro no se ha podido encontrar");
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorNombre")
    public String buscarLibroPorNombre(ModelMap modelo, @RequestParam String titulo) {
        try {
            Libro li = ls.buscarLibroPorTitulo(titulo);
            modelo.put("exito", "el libro encontrado es " + li);
        } catch (Exception a) {
            modelo.put("error", "el libro no se ha podido encontrar");
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorIsbn")
    public String buscarLibroPorIsbn(ModelMap modelo, @RequestParam Long isbn) {
        try {
            Libro li = ls.buscarLibroPorIsbn(isbn);
            modelo.put("exito", "el libro encontrado es " + li);
        } catch (Exception a) {
            modelo.put("error", "el libro no se ha podido encontrar");
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorAutor")
    public String buscarLibroPorAutor(ModelMap modelo, @RequestParam String autor) {
        try {
            List<Libro> li = ls.buscarLibroPorAutor(autor);

            modelo.put("exito", li);

        } catch (Exception a) {
            modelo.put("error", "el libro no se ha podido encontrar");
        }
        return "PaginaLibro.html";
    }

    @GetMapping("/buscarLibroPorEditorial")
    public String buscarLibroPorEditorial(ModelMap modelo, @RequestParam String editorial) {
        try {
            List<Libro> li = ls.buscarLibroPorEditorial(editorial);

            modelo.put("exito", li);

        } catch (Exception a) {
            modelo.put("error", "el libro no se ha podido encontrar");
        }
        return "PaginaLibro.html";
    }

}
