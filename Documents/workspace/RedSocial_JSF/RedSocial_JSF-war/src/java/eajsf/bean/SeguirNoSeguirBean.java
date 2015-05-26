package eajsf.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eajsf.entity.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@RequestScoped
public class SeguirNoSeguirBean {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    private String titulo;
    private String classBoton;
    
    private Usuario usuario;

    /**
     * Creates a new instance of SeguirNoSeguirBean
     */
    public SeguirNoSeguirBean() {
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getClassBoton() {
        return classBoton;
    }

    public void setClassBoton(String classBoton) {
        this.classBoton = classBoton;
    }
    
    

    public String tituloBoton(Usuario u){
        this.usuario=u;
        if(loginBean.getUsuario().siguesUsuario(u)){
            this.titulo="Siguiendo";
        }else{
            this.titulo="Seguir";
        }
        return titulo;
    }
    
    public String classBoton(){
        if(loginBean.getUsuario().siguesUsuario(this.usuario)){
            this.classBoton="btn btn-success";
        }else{
            this.classBoton="btn btn-primary";
        }
        return classBoton;
    }
    
    
   
}
