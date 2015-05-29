/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.ejb.PostFacade;
import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Post;
import eajsf.entity.Usuario;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jesus
 */
@ManagedBean
@RequestScoped
public class MuroBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private PostFacade postFacade;
    
    private List<Post> listaPostUsuarioMuro;
   
    private String cssDiv;
    
    
    private SimpleDateFormat format = new SimpleDateFormat("EEEEE dd MMM yyyy - HH:mm");
    private SimpleDateFormat formatSinHora = new SimpleDateFormat("EEE dd MMM yyyy");
    
    
    @PostConstruct
    public void init() {
//        Usuario usuario = loginBean.getUsuario();
//        Usuario usuarioMuro = loginBean.getUsuarioMuro();

        
        
        if(loginBean.getUsuario().equals(loginBean.getUsuarioMuro())){
            this.cssDiv="col-sm-6";
        }else{
            this.cssDiv="col-sm-6 centered";
        }
        
        
        
    }

    public SimpleDateFormat getFormat() {
        return format;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }

    public SimpleDateFormat getFormatSinHora() {
        return formatSinHora;
    }

    public void setFormatSinHora(SimpleDateFormat formatSinHora) {
        this.formatSinHora = formatSinHora;
    }
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public PostFacade getPostFacade() {
        return postFacade;
    }

    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
    }
    
    
    public String getCssDiv() {
        return cssDiv;
    }

    public void setCssDiv(String cssDiv) {
        this.cssDiv = cssDiv;
    }

    public List<Post> getListaPostUsuarioMuro() {
        return listaPostUsuarioMuro;
    }

    public void setListaPostUsuarioMuro(List<Post> listaPostUsuarioMuro) {
        this.listaPostUsuarioMuro = listaPostUsuarioMuro;
    }
    
    public String muro(){
       
        loginBean.setUsuarioMuro(loginBean.getUsuario());
        
        return "muro";
    }
    
    public String cargarUsuarioMuro(Usuario u){
        loginBean.setUsuarioMuro(u);
        listaPostUsuarioMuro = (List) postFacade.findPostIdUsuarioOrder(u.getIdUsuario());
        
        return "muro";
    }
    
    public List<Post> listaPostUsuario(){
        List<Post> aux =null;
        if(loginBean.getUsuario().equals(loginBean.getUsuarioMuro())){
            aux=loginBean.getListaPostUsuario();
        }else{
            aux=listaPostUsuarioMuro;
        }
         return aux;
    }
    /**
     * Creates a new instance of MuroBean
     */
    public MuroBean() {
    }

}
