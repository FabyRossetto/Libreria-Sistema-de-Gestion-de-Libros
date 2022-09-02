/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreriaSpring.servicios;

import com.libreria.libreriaSpring.entidades.Editorial;
import com.libreria.libreriaSpring.repositorios.EditorialRepositorio;
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
public class EditorialServicio {
    @Autowired
    EditorialRepositorio er;

    @Transactional
    public Editorial CrearEditorial(String nombre) throws Exception {
//se le pasa por parametro lo que el usuario llena 
        try {
            validar(nombre);
            Editorial ed = new Editorial();
            
            ed.setNombre(nombre);
            ed.setAlta(true);

           return er.save(ed);

        } catch (Exception e) {
            System.out.println("No se ha podido crear la editorial");
            return null;
        }
    }

    @Transactional
    public Editorial modificarEditorial(String id, String nombreViejo,String nombreNuevo) throws Exception {

        validar(nombreViejo);
        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo

        Optional<Editorial> respuesta = er.findById(id);
        if (respuesta.isPresent()) {
            Editorial e = respuesta.get();
            //no se crea un autor sino que se busca ,mediante el repo, el autor por id.
            //Si la respuesta is present,entonces que la traiga y setee los atributos, sino que tire una excepcion
           
            e.setNombre(nombreNuevo);
            e.setAlta(true);
           return e;
        } else {
            throw new Exception("no se encontro ninguna editorial con ese identificador");
        }

    }

    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void darDeBajaEditorial(String id) throws Exception {
        Optional<Editorial> respuesta = er.findById(id);
        if (respuesta.isPresent()) {
            Editorial uno = respuesta.get();
            uno.setAlta(false);
            er.save(uno);
        } else {
            throw new Exception("no se encontro la editorial que desea dar de baja");
        }
    }

    public void validar(String nombre) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception(" El nombre no puede ser nulo");
        }

    }

    public Editorial buscarEditorialPorId(String id) throws Exception {
try{
        Optional<Editorial> respuesta = er.findById(id);
       
            Editorial e = respuesta.get();
            return e;
        }catch(Exception e){
            throw new Exception("La editorial no se ha podido encontrar");
        }
       
    }

    public  Editorial buscarEditorialPorNombre(String nombre) throws Exception {
        validar(nombre);
        Editorial  Editorial= er.buscarEditorialPorNombre(nombre);
                
        return Editorial;
    }
    
    
    public List <Editorial>listarEditorial(){
        
        List<Editorial> a =er.findAll();
       
        return a;
        
    }
}
    

