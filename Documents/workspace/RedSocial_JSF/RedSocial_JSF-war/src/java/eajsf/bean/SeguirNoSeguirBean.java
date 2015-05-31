package eajsf.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@RequestScoped
public class SeguirNoSeguirBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    
    
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

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    
    
    
    

    public String tituloBoton(Usuario u){
         usuario=u;
        if(loginBean.getUsuario().siguesUsuario(usuario)){
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
    
    public void seguirDejarSeguir(Usuario u){
        usuario=u;
        String tit = tituloBoton(u);
            if(tit.equals("Siguiendo")){
               usuario.getUsuarioCollection1().remove(loginBean.getUsuario());
                loginBean.getUsuario().getUsuarioCollection().remove(usuario);
                
                loginBean.setIdListaUsuarios("Seguir");
                usuarioFacade.edit(loginBean.getUsuario());
                usuarioFacade.edit(usuario);
                if(loginBean.getIdListaUsuarios().equals("Siguiendo")){
                    loginBean.getListaUsuarios().remove(usuario);
                }
                
            }else if(tit.equals("Seguir")){
                usuario.getUsuarioCollection1().add(loginBean.getUsuario());
                loginBean.getUsuario().getUsuarioCollection().add(usuario);
            
           
                usuarioFacade.edit(loginBean.getUsuario());
                usuarioFacade.edit(usuario);
               
            }
            
            
//            return "seguidores_siguiendo";
        
    }
    
    
   
}
