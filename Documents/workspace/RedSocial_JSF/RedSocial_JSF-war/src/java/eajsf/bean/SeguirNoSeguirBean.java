/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.ejb.GrupoFacade;
import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author fran
 */
@ManagedBean
@RequestScoped
public class SeguirNoSeguirBean {

    /**
     * Creates a new instance of SeguirNoSeguirBean
     */
    public SeguirNoSeguirBean() {
    }
   
}
