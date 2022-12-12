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
        
            Autor existente= ar.buscarAutorPorNombre(nombre);
            if (existente==null){
            
            Autor a = new Autor();
           
            a.setNombre(nombre);
            a.setAlta(true);
            return ar.save(a);
            }else{
               return ar.save(existente);
                
            }
       
        } catch (Exception e) {
            System.out.println("No se ha podido crear el autor");
            return null;
        }
    }

    @Transactional
    public Autor modificarAutor(String nombreViejo,String nombreNuevo) throws Exception {
       //no lo valido porque uso este metodo en el modificra libro,y si el autor esta vacio no me deja modificarlo.
         try{
         Autor modificar = buscarAutorPorNombre(nombreViejo);
         
            modificar.setNombre(nombreNuevo);
            modificar.setAlta(true);
            return modificar;
         }catch(Exception e){
            Autor autorNuevo = CrearAutor(nombreNuevo);
            return autorNuevo;
         }
        
    }
    

    @Transactional// cualquier manejo que se haga con la db es una transaccion
    public void darDeBajaAutor(String id) throws Exception {
        Optional<Autor> respuesta = ar.findById(id);
        if (respuesta.isPresent()) {
            Autor a = respuesta.get();
            ar.delete(a);
            
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
        try{
        validar(nombre);
        Autor autor= ar.buscarAutorPorNombre(nombre);
                
        return autor;
    }catch(Exception e){
        return null;
    }
    }
    
    //metodo listar para hacer la tabla
    public List<Autor>listarAutor(){
        
        List<Autor> a=ar.findAll();
       
        return a;
        
    }
}
