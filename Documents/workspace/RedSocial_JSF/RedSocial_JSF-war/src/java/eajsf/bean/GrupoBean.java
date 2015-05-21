/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.bean;

import eajsf.ejb.GrupoFacade;
import eajsf.ejb.PostFacade;
import eajsf.ejb.UsuarioFacade;
import eajsf.entity.Grupo;
import eajsf.entity.Post;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import java.util.List;
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
public class GrupoBean {

    @EJB
    private GrupoFacade grupoFacade;
    @EJB
    private PostFacade postFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    // Id usuarios session
    private BigDecimal idUsuario;
    private BigDecimal idUsuarioMuro;

    // Usuarios session
    private Usuario usuario;
    private Usuario usuarioMuro;

    // Atributos a mostrar en grupo.xhtml
    private List<Grupo> listaGruposUsuarioMuro = null;
    private Grupo grupo = null;
    private List<Post> listaPostGrupo = null;
    private List<Usuario> listaMiembrosGrupo = null;

    private Boolean muroDeOtro;
    private Boolean tieneGrupos;
    
    // atributos para crear post
    private String descripcionPostGrupo;
    private String imagenPostGrupo;

    private String idGrupoElegido;
    private String idPostEditar;
    
    // para el form del listado grupo
    private String idGrupoAbandonar;

    /**
     * Creates a new instance of GrupoBean
     */
    public GrupoBean() {
    }

    public GrupoFacade getGrupoFacade() {
        return grupoFacade;
    }

    public void setGrupoFacade(GrupoFacade grupoFacade) {
        this.grupoFacade = grupoFacade;
    }

    public PostFacade getPostFacade() {
        return postFacade;
    }

    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioMuro() {
        return usuarioMuro;
    }

    public void setUsuarioMuro(Usuario usuarioMuro) {
        this.usuarioMuro = usuarioMuro;
    }

    public List<Grupo> getListaGruposUsuarioMuro() {
        return listaGruposUsuarioMuro;
    }

    public void setListaGruposUsuarioMuro(List<Grupo> listaGruposUsuarioMuro) {
        this.listaGruposUsuarioMuro = listaGruposUsuarioMuro;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Post> getListaPostGrupo() {
        return listaPostGrupo;
    }

    public void setListaPostGrupo(List<Post> listaPostGrupo) {
        this.listaPostGrupo = listaPostGrupo;
    }

    public List<Usuario> getListaMiembrosGrupo() {
        return listaMiembrosGrupo;
    }

    public void setListaMiembrosGrupo(List<Usuario> listaMiembrosGrupo) {
        this.listaMiembrosGrupo = listaMiembrosGrupo;
    }

    public Boolean getMuroDeOtro() {
        return muroDeOtro;
    }

    public void setMuroDeOtro(Boolean muroDeOtro) {
        this.muroDeOtro = muroDeOtro;
    }

    public Boolean getTieneGrupos() {
        return tieneGrupos;
    }

    public void setTieneGrupos(Boolean tieneGrupos) {
        this.tieneGrupos = tieneGrupos;
    }

    public String getIdGrupoElegido() {
        return idGrupoElegido;
    }

    public void setIdGrupoElegido(String idGrupoElegido) {
        this.idGrupoElegido = idGrupoElegido;
    }

    public String getIdPostEditar() {
        return idPostEditar;
    }

    public void setIdPostEditar(String idPostEditar) {
        this.idPostEditar = idPostEditar;
    }

    public String getDescripcionPostGrupo() {
        return descripcionPostGrupo;
    }

    public void setDescripcionPostGrupo(String descripcionPostGrupo) {
        this.descripcionPostGrupo = descripcionPostGrupo;
    }

    public String getImagenPostGrupo() {
        return imagenPostGrupo;
    }

    public void setImagenPostGrupo(String imagenPostGrupo) {
        this.imagenPostGrupo = imagenPostGrupo;
    }

    public String getIdGrupoAbandonar() {
        return idGrupoAbandonar;
    }

    public void setIdGrupoAbandonar(String idGrupoAbandonar) {
        this.idGrupoAbandonar = idGrupoAbandonar;
    }

    
    // Metodos gestores
    @PostConstruct
    public void init() {
        //Cambiar por el bean de sesion
        idUsuario = new BigDecimal(1);
        idUsuarioMuro = new BigDecimal(1);

        // Recuperamos los usuarios de la base de datos
        if (idUsuario.equals(idUsuarioMuro)) {
            usuario = usuarioFacade.find(idUsuario);
            usuarioMuro = usuario;
        } else {
            usuario = usuarioFacade.find(idUsuario);
            usuarioMuro = usuarioFacade.find(idUsuario);
        }

        // True si estamos viendo un muro de otra persona
        muroDeOtro = !idUsuario.equals(idUsuarioMuro);

        if (muroDeOtro) {
            listaGruposUsuarioMuro = (List) usuarioFacade.gruposPublicosDeUsuario(usuarioMuro);
        } else {
            listaGruposUsuarioMuro = (List) usuarioMuro.getGrupoCollection();
        }

        tieneGrupos = listaGruposUsuarioMuro.size() > 0;
        
        Grupo g = grupoFacade.find(new BigDecimal(1));

        if (tieneGrupos) {
            if (idGrupoElegido != null) {
                // Recuperamos el grupo clickado
                BigDecimal idGrupo = new BigDecimal(idGrupoElegido);
                grupo = (Grupo) grupoFacade.find(idGrupo);
            } else {
                // Mostramos el primer grupo
                grupo = (Grupo) grupoFacade.find(listaGruposUsuarioMuro.get(0).getIdGrupo());
            }
            listaPostGrupo = (List) postFacade.getListaPostGrupo(grupo.getIdGrupo());
            listaMiembrosGrupo = (List) grupo.getUsuarioBloqueadoCollection();
        }

    }
    
    public String doPostGrupoCrear() {
        return "PostCreateBean";
    }
    
    public String doPostGrupoEdit(){
        return "PostEditBean";
    }
    
    public String doGrupoElegido() {
        idGrupoElegido = getParameter("idGrupoElegido");
        BigDecimal idGrupo = new BigDecimal(idGrupoElegido);
        grupo = (Grupo) grupoFacade.find(idGrupo);
        return "grupo";
    }
    
    public String doCrearGrupo(){
        return "crearGrupo?idUsuarioMuro=" + idUsuarioMuro;
    }
    
    // Mirar a ver que tal
    public String doAbandonarGrupo() {
        return "seguirNoSeguirBean?idGrupoAbandonar=" + idGrupoAbandonar;
    }
    
    
    // Metodos auxiliares
    private String getParameter(String name){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
    
    
    

}
