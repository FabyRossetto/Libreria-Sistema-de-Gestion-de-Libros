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
            Editorial existe=er.buscarEditorialPorNombre(nombre);
            if(existe==null){
            Editorial ed = new Editorial();
            
            ed.setNombre(nombre);
            ed.setAlta(true);

           return er.save(ed);
            }else{
                return er.save(existe);
            }
        } catch (Exception e) {
            System.out.println("No se ha podido crear la editorial");
            return null;
        }
    }

    @Transactional
    public Editorial modificarEditorial(String nombreViejo,String nombreNuevo) throws Exception {
       try{
       Editorial modificar = buscarEditorialPorNombre(nombreViejo);
         
            modificar.setNombre(nombreNuevo);
            modificar.setAlta(true);
            return modificar;
        }catch(Exception e){
            Editorial ediNueva = CrearEditorial(nombreNuevo);
            return ediNueva;
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
    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void EliminarEditorial(String id) throws Exception {
        Optional<Editorial> respuesta = er.findById(id);
        if (respuesta.isPresent()) {
            Editorial uno = respuesta.get();
            er.delete(uno);
        } else {
            throw new Exception("no se pudo eliminar esta editorial.Si la misma esta vinculada a un libro asegurese de eliminar primero el libro");
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
    

