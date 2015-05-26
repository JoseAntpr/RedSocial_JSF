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
    
    Usuario usuario;

    /**
     * Creates a new instance of SeguirNoSeguirBean
     */
    public SeguirNoSeguirBean() {
    }
   
}
