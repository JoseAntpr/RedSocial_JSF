package eajsf.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eajsf.ejb.GrupoFacade;
import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Grupo;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    private GrupoFacade grupoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    private String titulo;
    private String classBoton;
    
    private Usuario usuario;
    
    private Grupo grupo;
    
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

    public GrupoFacade getGrupoFacade() {
        return grupoFacade;
    }

    public void setGrupoFacade(GrupoFacade grupoFacade) {
        this.grupoFacade = grupoFacade;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
    
    public void unirAbandonarGrupo(Grupo g){
        // Es miembro
        Usuario u = loginBean.getUsuario();
        if(u.getGrupoCollection1().contains(g)){
            u.getGrupoCollection1().remove(g);
            g.getUsuarioCollection1().remove(u);
            usuarioFacade.edit(u);
            grupoFacade.edit(g);
        }else{
            u.getGrupoCollection1().add(g);
            g.getUsuarioCollection1().add(u);
            usuarioFacade.edit(u);
            grupoFacade.edit(g);
        }
    }
            

    public String getDatosForm(Grupo g, String s) {
        String res = "";
        boolean publico = g.getPrivacidad().equals(BigInteger.ZERO);
        boolean miembro = loginBean.getUsuario().getGrupoCollection1().contains(g);
        if (s.equals("estadoEnGrupo")) {
            if (publico) {
                res += "Grupo público";
            } else {
                res += "Grupo privado";
            }
            if (miembro) {
                res += ", eres miembro.";
            } else {
                res += ", no eres miembro.";
            }
        } else if (s.equals("claseBoton")) {
            if (miembro) {
                res += "btn btn-danger pull-right";
            } else {
                res += "btn btn-success pull-right";
            }
        } else if (s.equals("textoBoton")) {
            if (miembro) {
                res = "Abandonar";
            } else {
                res = "Unirse";
            }
        }
        return res;
    }
   
    public boolean mostrarBoton(Grupo g){
        boolean res = true;
        boolean miembro = g.getUsuarioCollection1().contains(loginBean.getUsuario());
        if(!miembro && g.getPrivacidad().equals(BigInteger.ONE)){
            res = false;
        }
        return res;
    }
}
