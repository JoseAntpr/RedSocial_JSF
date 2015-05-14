/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.beans;

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
    
    private String email;
    private String password;
    private BigDecimal idUsuario;
    private BigDecimal idUsuarioMuro;
    private String error=null;

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
    
    
    
    
    public String comprobarUsuario(){
        
        Usuario usr = usuarioFacade.login(this.email, this.password);
        String ruta=null;
        
        if(usr != null){
            idUsuario= usr.getIdUsuario();
            idUsuarioMuro=usr.getIdUsuario();
            ruta="muro";
        }else{
            error="Nombre de usuario o contraseña incorrectos, vuelve a intentarlo porfavor";
            ruta="login";
        }
       return ruta; 
    }
    
    public String registrarUsuario(){
        return null;
        
    }
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
}
