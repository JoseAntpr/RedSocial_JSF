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
    
    private List<Post> listaPost;

    @PostConstruct
    public void init() {
        Usuario usuario = loginBean.getUsuario();
        Usuario usuarioMuro = loginBean.getUsuarioMuro();

        List<Post> listaPost = null;
        List<Post> postSigues = null;

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

    public List<Post> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Post> listaPost) {
        this.listaPost = listaPost;
    }
    
    

    /**
     * Creates a new instance of MuroBean
     */
    public MuroBean() {
    }

}
