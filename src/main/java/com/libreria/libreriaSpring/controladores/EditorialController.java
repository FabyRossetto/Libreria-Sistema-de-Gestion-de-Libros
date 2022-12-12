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
@RequestMapping("")
public class EditorialController {

    @Autowired
    EditorialServicio es;

    

    @PostMapping("/registrarEditorial")//cuando entra a la barra del servidor ejecuta el metodo
    public String GuardarEditorial(ModelMap Modelo, @RequestParam String nombre) throws Exception {
        try {
            es.CrearEditorial(nombre);
            Modelo.put("exito", "su editorial ya se encuentra guardada en nuestra base de datos");
            Modelo.addAttribute("nombre", es.listarEditorial());
        } catch (Exception e) {
            Modelo.put("error", "hubo un error al registrar la editorial");
            Modelo.addAttribute("nombre", es.listarEditorial());
        }
        return "PaginaEditorial.html";
    }

    @GetMapping("/editarEditorial")
    public String EditarEditorial(ModelMap modelo) {
       modelo.addAttribute("nombre",es.listarEditorial()); 
        return "PaginaEditorial.html";
    }

    @PostMapping("/editarEditorial")
    public String editarEditorial(ModelMap modelo, @RequestParam String nombreViejo, @RequestParam String nombreNuevo) throws Exception {
        try {
            es.modificarEditorial(nombreViejo, nombreNuevo);
            modelo.put("exito", "su editorial ha sido editada con exito");
            modelo.addAttribute("nombre", es.listarEditorial());
        } catch (Exception a) {
            modelo.put("error", "no se ha podido editar la editorial");
            modelo.addAttribute("nombre", es.listarEditorial());
        }

        return "PaginaEditorial.html";
    }

    @GetMapping("/eliminarEditorial")
    public String Eliminar(ModelMap modelo) {
    modelo.addAttribute("nombre",es.listarEditorial());
        return "PaginaEditorial.html";
    }

    @PostMapping("/eliminarEditorial")
    public String Eliminar(ModelMap modelo, @RequestParam String id) throws Exception {
        try {
            es.EliminarEditorial(id);
            modelo.put("exito", "su editorial ha sido dada de baja");
            modelo.addAttribute("nombre", es.listarEditorial());

        } catch (Exception a) {
            modelo.put("error", "no se pudo eliminar esta editorial.Si la misma esta vinculada a un libro asegurese de eliminar primero el libro");
            modelo.addAttribute("nombre", es.listarEditorial());
        }

        return "PaginaEditorial.html";
    }

    @GetMapping("/buscarEditorialPorid")
    public String buscarEditorialPorId(ModelMap modelo, @RequestParam String id) {
        try {
            Editorial edi = es.buscarEditorialPorId(id);
            modelo.put("exito", "la editorial encontrada es" + edi);
            modelo.addAttribute("nombre", es.listarEditorial());
        } catch (Exception a) {
            modelo.put("error", "no se ha encontrado su editorial");
            modelo.addAttribute("nombre", es.listarEditorial());
        }
        return "PaginaEditorial.html";
    }

    @GetMapping("/buscarEditorialPorNombre")
    public String buscarEditorialPorNombre(ModelMap modelo, @RequestParam String nombre) {
        try {
            Editorial e = es.buscarEditorialPorNombre(nombre);

            if (e != null) {
                modelo.put("exito", "la editorial encontrada es " + e);
                modelo.addAttribute("nombre", es.listarEditorial());

            }
            if (e == null) {
                modelo.put("error", "la editorial no se ha podido encontrar");
                modelo.addAttribute("nombre", es.listarEditorial());
            }
        } catch (Exception a) {
            return null;
        }
        return "PaginaEditorial.html";
    }
}
