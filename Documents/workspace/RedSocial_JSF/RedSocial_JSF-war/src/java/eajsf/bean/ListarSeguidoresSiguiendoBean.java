/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.entity.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@RequestScoped
public class ListarSeguidoresSiguiendoBean {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
   
    

    /**
     * Creates a new instance of ListarSeguidoresSiguiendoBean
     */
    public ListarSeguidoresSiguiendoBean() {
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

   
    
    public String cargarSeguidores(){
        loginBean.setListaUsuarios((List) loginBean.getUsuarioMuro().getUsuarioCollection1());
        
        return "seguidores_siguiendo";
    }
    public String cargarSigues(){
        loginBean.setListaUsuarios((List) loginBean.getUsuarioMuro().getUsuarioCollection()) ;
        return "seguidores_siguiendo";
    }
    
    
    
}
