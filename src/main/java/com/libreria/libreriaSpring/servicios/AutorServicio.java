   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.servicios;

import com.libreria.libreriaSpring.entidades.Autor;
import com.libreria.libreriaSpring.repositorios.AutorRepositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabi
 */
@Service
public class AutorServicio {
    @Autowired
    AutorRepositorio ar;

    @Transactional(propagation =Propagation.REQUIRED,rollbackFor={Exception.class})
    public Autor CrearAutor(String nombre) throws Exception {
//se le pasa por parametro lo que el usuario llena 
        try {
            validar(nombre);
            Autor a = new Autor();
           
            a.setNombre(nombre);
            a.setAlta(true);

            
        return ar.save(a);
        } catch (Exception e) {
            System.out.println("No se ha podido crear el autor");
            return null;
        }
    }

    @Transactional
    public void modificarAutor(String id, String nombreViejo,String nombreNuevo) throws Exception {
       
         validar(nombreViejo);
        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo
        Optional <Autor> respuesta = ar.findById(id);
        if (respuesta.isPresent()) {
            Autor au = respuesta.get();
            //no se crea un autor sino que se busca ,mediante el repo, el autor por id.
            //Si la respuesta is present,entonces que la traiga y setee los atributos, sino que tire una excepcion
           
            au.setNombre(nombreNuevo);
            au.setAlta(true);

        } else {
            throw new Exception("no se encontro ningun autor con ese identificador");
        }

    }

    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void darDeBajaAutor(String id) throws Exception {
        Optional<Autor> respuesta = ar.findById(id);
        if (respuesta.isPresent()) {
            Autor a = respuesta.get();
            a.setAlta(false);
            ar.save(a);
        } else {
            throw new Exception("no se encontro el autor que desea dar de baja");
        }
    }

    public void validar(String nombre) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception(" El nombre no puede ser nulo");
        }

    }

    public Autor buscarAutorPorId(String id) throws Exception {
try{
        Optional<Autor> respuesta = ar.findById(id);
       
            Autor a = respuesta.get();
           
            return a;
        } catch(Exception e){
            throw new Exception("El autor no se ha podido encontrar");
        }
    }
    
    public Autor buscarAutorPorNombre(String nombre) throws Exception{
        validar(nombre);
        Autor autor= ar.buscarAutorPorNombre(nombre);
                
        return autor;
    }
    
    //metodo listar para hacer la tabla
    public List<Autor>listarAutor(){
        
        List<Autor> a=ar.findAll();
       
        return a;
        
    }
}
