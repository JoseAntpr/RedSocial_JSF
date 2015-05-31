/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.ejb.GrupoFacade;
import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Grupo;
import eajsf.entity.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@RequestScoped
public class BuscarBean {
    @EJB
    private GrupoFacade grupoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    private List<Grupo> listaGrupos;
    private String datos;
    
    /**
     * Creates a new instance of BuscarBean
     */
    public BuscarBean() {
    }

    public GrupoFacade getGrupoFacade() {
        return grupoFacade;
    }

    public void setGrupoFacade(GrupoFacade grupoFacade) {
        this.grupoFacade = grupoFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    
    
    public String buscar()  {
        loginBean.setListaUsuarios((List) usuarioFacade.buscarUsuarios(datos));
        return "busqueda";
    }   
    
}
