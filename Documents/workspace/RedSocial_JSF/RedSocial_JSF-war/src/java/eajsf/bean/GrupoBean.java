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
import java.util.Map;
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

    private Boolean muroPropio;
    private Boolean tieneGrupos;
    
    // atributos para crear post
    private String descripcionPostGrupo;
    private String imagenPostGrupo;

    private String idGrupoElegido;
    private String idPostEditar;
    
    private boolean esAdministradorGrupo;

    private Post postABorrar;
    private Post postAEditar;
    
    private Grupo grupoAAbandonar;
    

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

    public Boolean getMuroPropio() {
        return muroPropio;
    }

    public void setMuroPropio(Boolean muroPropio) {
        this.muroPropio = muroPropio;
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

    public boolean isEsAdministradorGrupo() {
        return esAdministradorGrupo;
    }

    public void setEsAdministradorGrupo(boolean esAdministradorGrupo) {
        this.esAdministradorGrupo = esAdministradorGrupo;
    }

    public Post getPostABorrar() {
        return postABorrar;
    }

    public void setPostABorrar(Post postABorrar) {
        this.postABorrar = postABorrar;
    }

    public Post getPostAEditar() {
        return postAEditar;
    }

    public void setPostAEditar(Post postAEditar) {
        this.postAEditar = postAEditar;
    }

    public Grupo getGrupoAAbandonar() {
        return grupoAAbandonar;
    }

    public void setGrupoAAbandonar(Grupo grupoAAbandonar) {
        this.grupoAAbandonar = grupoAAbandonar;
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
        muroPropio = idUsuario.equals(idUsuarioMuro);

        if (muroPropio) {
            listaGruposUsuarioMuro = (List) usuarioMuro.getGrupoCollection1();
        } else {
            listaGruposUsuarioMuro = (List) usuarioFacade.gruposPublicosDeUsuario(usuarioMuro);
        }

        tieneGrupos = listaGruposUsuarioMuro.size() > 0;
        idGrupoElegido = getParameter("idGrupoElegido");

        if (tieneGrupos) {
            if (idGrupoElegido != null) {
                // Recuperamos el grupo clickado
                BigDecimal idGrupo = new BigDecimal(idGrupoElegido);
                grupo = (Grupo) grupoFacade.find(idGrupo);
            } else {
                // Mostramos el primer grupo
                grupo = (Grupo) grupoFacade.find(listaGruposUsuarioMuro.get(0).getIdGrupo());
            }
            
            esAdministradorGrupo = idUsuario.toBigInteger().equals(grupo.getIdAdministradorG());
            
            listaPostGrupo = (List) postFacade.getListaPostGrupo(grupo.getIdGrupo());
            //listaPostGrupo = (List) grupo.getPostCollection();
            listaMiembrosGrupo = (List) grupo.getUsuarioCollection1();
        }

    }
    
    public String doCrearGrupo(){
        return "crearGrupo?idUsuario=" + idUsuario;
    }
    
    public String doEditarGrupo(){
        // Falta implementar el xhtml y el bean
        return "editarGrupo?idGrupo=" + grupo.getIdGrupo();
    }
    
    public String doEliminarGrupo(){
        if(muroPropio && esAdministradorGrupo){
            grupoFacade.eliminarGrupo(grupo, usuario);
            // Falta actualizar los usuarios de la sesión
        }
        return "grupo";
    }
    
    // Mirar a ver que tal
    public String doAbandonarGrupo() {
        grupoFacade.abandonarGrupo(grupoAAbandonar, usuario);
        // Actualizar usuario de la sesion
        usuarioMuro = usuario;
        return "grupo";
    }
    
    public String doAnadirEliminarMiembro(){
        // Falta implementar el xhtml y el bean
        return "addDeleteMiembroGrupo?idGrupo=" + grupo.getIdGrupo();
    }
    
    public String doPostGrupoCrear() {
        // Ajax
        // Falta
        return null;
    }
    
    public String doPostGrupoEditar(){
        // Ajax
        // Falta
        return null;
    }
    
    public String doPostGrupoEliminar(){
        // Ajax
        postFacade.deletePostGrupo(postABorrar, usuario);
        return null;
    }
    
    // Metodos auxiliares
    private String getParameter(String name){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
    
    
    

}
