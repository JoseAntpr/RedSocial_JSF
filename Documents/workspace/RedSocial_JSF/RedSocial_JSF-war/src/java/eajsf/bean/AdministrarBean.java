/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Usuario;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Azahar
 */
@Named(value = "administrarBean")
@Dependent
public class AdministrarBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuario;
   
    private String nombreBoton;
    private String classBoton;
     
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public String getNombreBoton() {
        return nombreBoton;
    }

    public void setNombreBoton(String nombreBoton) {
        this.nombreBoton = nombreBoton;
    }   

    public String getClassBoton() {
        return classBoton;
    }

    public void setClassBoton(String classBoton) {
        this.classBoton = classBoton;
    }
            
   public String nombreBoton(Usuario u){
        usuario = u;
        if(usuarioFacade.estaBloqueado(usuario)){
            nombreBoton="Desbloquear";            
        }else{
            nombreBoton="Bloquear";                
        }     
        return nombreBoton;
    }
    public String classBoton(Usuario u){
        usuario = u;
        if(usuarioFacade.estaBloqueado(usuario)){
           classBoton="btn btn-success";
        }else{
            classBoton="btn btn-danger";            
        }     
        return classBoton;
    }
    
    public void bloquear(Usuario u){
        usuario = u;
        if(!usuarioFacade.estaBloqueado(usuario)){
           usuario.setBloqueado("t");
           usuarioFacade.edit(usuario);
        }else{
            usuario.setBloqueado("f"); 
            usuarioFacade.edit(usuario);
        }     
    }
   
    public AdministrarBean() {
    }
    
}

