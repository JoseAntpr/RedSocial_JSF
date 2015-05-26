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

/**
 *
 * @author Azahar
 */
@Named(value = "usuarioBean")
@Dependent
public class UsuarioBean {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    
    private String nombre;
    private String apellidos;
    private String direccion;
    private String localidad;
    private String provincia;
    private String pais;
    private String email;
    private String password;

    private Usuario usuarioEditar;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    
    public String modificarUsuario(){
          
        System.out.println(usuarioEditar.getIdUsuario());
        usuarioEditar.setNombre(nombre);
        usuarioEditar.setApellidos(apellidos);
        usuarioEditar.setDireccion(direccion);
        usuarioEditar.setLocalidad(localidad);
        usuarioEditar.setProvincia(provincia);
        usuarioEditar.setPais(pais);
        usuarioEditar.setEmail(email);
        
        usuarioFacade.edit(usuarioEditar);               
        
        return "muro.xhtml";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }
    
    public String botonEditarPerfil(){
       //setEditUsuario(usuarioFacade.find(id));
       return "editarUsuario.xhtml";
    }
    
}
