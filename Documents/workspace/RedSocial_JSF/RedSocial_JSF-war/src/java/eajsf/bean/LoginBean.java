/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@SessionScoped
public class LoginBean {

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
    private BigDecimal idUsuario;
    private BigDecimal idUsuarioMuro;
    private String error = null;

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
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

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getIdUsuarioMuro() {
        return idUsuarioMuro;
    }

    public void setIdUsuarioMuro(BigDecimal idUsuarioMuro) {
        this.idUsuarioMuro = idUsuarioMuro;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String comprobarUsuario() {

        Usuario usr = usuarioFacade.login(this.email, this.password);
        String ruta = null;

        if (usr != null) {
            idUsuario = usr.getIdUsuario();
            idUsuarioMuro = usr.getIdUsuario();
            ruta = "muro";
        } else {
            error = "Nombre de usuario o contraseña incorrectos, vuelve a intentarlo porfavor";
            ruta = "login";
        }
        return ruta;
    }

    public String registrarUsuario() {
        String ruta = null;
        Usuario usr = usuarioFacade.buscarEmail(email);

        if (usr == null) {
            usr = usuarioFacade.nuevoUser(this.nombre, this.apellidos, this.direccion, this.localidad, this.provincia, this.pais, this.email, this.password);
            idUsuario = usr.getIdUsuario();
            idUsuarioMuro = usr.getIdUsuario();
            ruta = "muro";
        } else {
            error = "El email ya esta registrado en nuestra red social.";
            ruta = "login";
        }

        return ruta;

    }
    
    public Usuario buscarID(BigDecimal id){
        return (Usuario)usuarioFacade.find(id);
    }
    
    public boolean sigues(Usuario u, Usuario u2){
        return u.siguesUsuario(u2);
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

}