/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.servicios;


import com.libreria.libreriaSpring.entidades.Autor;
import com.libreria.libreriaSpring.entidades.Editorial;
import com.libreria.libreriaSpring.entidades.Libro;
import com.libreria.libreriaSpring.repositorios.AutorRepositorio;
import com.libreria.libreriaSpring.repositorios.EditorialRepositorio;
import com.libreria.libreriaSpring.repositorios.LibroRepositorio;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabi
 */
@Service
public class LibroServicio {

    @Autowired
    LibroRepositorio lr;
    @Autowired
    AutorServicio as;
    @Autowired
    EditorialServicio es;
   
   

    @Transactional
    public Libro CrearLibro(String titulo,Long isbn, Integer anio, String nombreAutor, String nombreEdit) throws Exception {
//se le pasa por parametro lo que el usuario llena 
        try {
            validar( titulo, isbn,anio, nombreAutor, nombreEdit);
            Libro li = new Libro();
            
            li.setTitulo(titulo);
            li.setIsbn(isbn);
            li.setAnio(anio);
            li.setAlta(true);
            li.setAutor(as.CrearAutor(nombreAutor));
            li.setEditorial(es.CrearEditorial(nombreEdit));

            return lr.save(li);

        } catch (Exception e) {
            System.out.println("No se a podido crear el libro");
            return null;
        }
    }

    @Transactional
    public void modificarLibro(String id, Long isbn, String tituloNuevo, Integer anio, String autorViejo, String autorNuevo,String editVieja, String editNueva) throws Exception {

       
        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo

        Optional<Libro> respuesta = lr.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            //no se crea un libro sino que se busca ,mediante el repo, el libro por id.
            //Si la respuesta is present,entonces que la traiga y setee los atributos, sino que tire una excepcion
            libro.setIsbn(isbn);
            libro.setTitulo(tituloNuevo);
            libro.setAnio(anio);
            libro.setAutor(as.modificarAutor(autorViejo, autorNuevo));
            libro.setEditorial(es.modificarEditorial(editVieja, editNueva));
            libro.setAlta(true);

            lr.save(libro);
        } else {
            throw new Exception("no se encontro ningun libro con ese identificador");
        }

    }

    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void darDeBajaLibro(String id) throws Exception {
        Optional<Libro> respuesta = lr.findById(id);
        if (respuesta.isPresent()) {
            Libro l = respuesta.get();
            l.setAlta(false);
            lr.save(l);
        } else {
            throw new Exception("no se encontro el libro que desea dar de baja");
        }
    }
    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void EliminarLibro(String id) throws Exception {
        Optional<Libro> respuesta = lr.findById(id);
        if (respuesta.isPresent()) {
            Libro l = respuesta.get();
            lr.delete(l);
        } else {
            throw new Exception("no se encontro el libro que desea dar de baja");
        }
    }

    public void validar( String titulo,Long isbn, Integer anio, String nombreAutor, String nombreEdit) throws Exception {

       
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception(" El titulo no puede ser nulo");
        }
         if (isbn == null || isbn.toString().trim().isEmpty() || isbn.toString().length() < 4) {

            throw new Exception(" El isbn no puede ser nulo,ni tener menos de 4 caracteres");
        }
        if (anio == null || anio.toString().trim().isEmpty() || anio > 2022) {
            throw new Exception(" El año no puede ser nulo,ni futuro");
        }

        if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
            throw new Exception(" El autor no puede ser nulo)");
        }
        if (nombreEdit == null || nombreEdit.trim().isEmpty()) {
            throw new Exception(" La editorial no puede ser nula)");
        }

    }

    public Libro buscarLibroPorId(String id) throws Exception {
        try {
            Optional<Libro> respuesta = lr.findById(id);

            Libro l = respuesta.get();
            return l;
        } catch (Exception e) {
            throw new Exception("El libro no se ha podido encontrar");
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) throws Exception {
       try{      
        Libro libro = lr.buscarLibroPorTitulo(titulo);

        return libro;
       }catch(Exception e){ 
           return null;
       }
    }

    public Libro buscarLibroPorIsbn(Long isbn) throws Exception {

        Libro libro = lr.buscarLibroPorIsbn(isbn);

        return libro;
    }

    public List<Libro> buscarLibroPorAutor(String autor) throws Exception {

        List<Libro> libro = lr.buscarPorAutor(autor);

        return libro;
    }

    public List<Libro> buscarLibroPorEditorial(String editorial) throws Exception {

        List<Libro> libro = lr.buscarPorEditorial(editorial);

        return libro;
    }

    public List<Libro> listarLibro() {
       
        List<Libro> l = lr.findAll();


        return l;

    }
}