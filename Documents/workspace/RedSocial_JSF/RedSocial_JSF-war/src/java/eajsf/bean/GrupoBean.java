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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

// Imports for tomahawk
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.bean.ManagedProperty;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.myfaces.custom.fileupload.UploadedFile;

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

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

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
    private String desPostGrupo;
    private String imagenPostGrupoUri;
    private Part imagenFile;
//    private UploadedFile uploadedImage;

    private String idGrupoElegido;
    private String idPostEditar;

    private boolean esAdministradorGrupo;

    private Post postABorrar;
    private Post postAEditar;
    private boolean editarPost;
    private String idPostToAjax;

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

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
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

    public String getDesPostGrupo() {
        return desPostGrupo;
    }

    public void setDesPostGrupo(String desPostGrupo) {
        this.desPostGrupo = desPostGrupo;
    }

    public String getImagenPostGrupoUri() {
        return imagenPostGrupoUri;
    }

    public void setImagenPostGrupoUri(String imagenPostGrupoUri) {
        this.imagenPostGrupoUri = imagenPostGrupoUri;
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

    public boolean isEditarPost() {
        return editarPost;
    }

    public void setEditarPost(boolean editarPost) {
        this.editarPost = editarPost;
    }

    public String getIdPostToAjax() {
        return idPostToAjax;
    }

    public void setIdPostToAjax(String idPostToAjax) {
        this.idPostToAjax = idPostToAjax;
    }

    public Part getImagenFile() {
        return imagenFile;
    }

    public void setImagenFile(Part imagenFile) {
        this.imagenFile = imagenFile;
    }

//    public UploadedFile getUploadedImage() {
//        return uploadedImage;
//    }
//
//    public void setUploadedImage(UploadedFile uploadedImage) {
//        this.uploadedImage = uploadedImage;
//    }
    // Metodos gestores
    @PostConstruct
    public void init() {
        //Cambiar por el bean de sesion
//        idUsuario = new BigDecimal(1);
//        idUsuarioMuro = new BigDecimal(1);
//
//        // Recuperamos los usuarios de la base de datos
//        if (idUsuario.equals(idUsuarioMuro)) {
//            usuario = usuarioFacade.find(idUsuario);
//            usuarioMuro = usuario;
//        } else {
//            usuario = usuarioFacade.find(idUsuario);
//            usuarioMuro = usuarioFacade.find(idUsuario);
//        }
        // Usuario de la sesion
        usuario = loginBean.getUsuario();
        usuarioMuro = loginBean.getUsuarioMuro();

        // True si estamos viendo un muro de otra persona
        muroPropio = usuario.getIdUsuario().equals(usuarioMuro.getIdUsuario());

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

            esAdministradorGrupo = usuario.getIdUsuario().toBigInteger().equals(grupo.getIdAdministradorG());

            listaPostGrupo = (List) postFacade.getListaPostGrupo(grupo.getIdGrupo());
            //listaPostGrupo = (List) grupo.getPostCollection();
            listaMiembrosGrupo = (List) grupo.getUsuarioCollection1();

            
        }

    }

    public String doCrearGrupo() {
//        return "crearGrupo?idUsuario=" + idUsuario;
        return "grupo";
    }

    public String doEditarGrupo() {
        // Falta implementar el xhtml y el bean
        return "editarGrupo?idGrupo=" + grupo.getIdGrupo();
    }

    public String doEliminarGrupo() {
        if (muroPropio && esAdministradorGrupo) {
//            grupoFacade.eliminarGrupo(grupo, usuario);
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

    public String doAnadirEliminarMiembro() {
        // Falta implementar el xhtml y el bean
//        return "addDeleteMiembroGrupo?idGrupo=" + grupo.getIdGrupo();
        return "grupo";
    }

    public String doPostGrupoCrear() throws IOException {
        Post p = new Post();
//        p.setDescripcion(desPostGrupo);
        p.setDescripcion(desPostGrupo);
        p.setFecha(new Date());
        p.setIdGrupoP(grupo);
        p.setIdUsuarioP(usuario);

        if (imagenFile != null) {
            p.setImagen(imagenPostGrupoUri);
        }

        // Añadimos el post a la DB
        postFacade.create(p);
        // Añadimos el post a la coleccion de post del grupo
        grupo.getPostCollection().add(p);
        // Actualizamos el grupo con el post ya añadido
        grupoFacade.edit(grupo);
        // Añadimos el post a la coleccion de post del miembro creador
        usuario.getPostCollection().add(p);
        // Actualizamos el usuario con el post ya añadido
        usuarioFacade.edit(usuario);
        listaPostGrupo.add(p);

        return null;
    }

    public void validarImagen(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part) value;
        if (file.getSize() > 1024 * 1024 * 10) {
            msgs.add(new FacesMessage("file too big"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public String doPostGrupoEditar() {
        postAEditar.setDescripcion(desPostGrupo);
        List<Post> lista = (List)usuario.getPostCollection();
        int posicion = posicionPost(postAEditar, lista);
        lista.get(posicion).setDescripcion(desPostGrupo);
        usuario.setPostCollection(lista);
        usuarioMuro.setPostCollection(lista);
        grupo.setPostCollection(lista);
        listaPostGrupo = lista;
        postFacade.edit(postAEditar);
        usuarioFacade.edit(usuario);
        grupoFacade.edit(grupo);

        // al final
        editarPost = false;
        return null;
    }

    public String doPostGrupoEliminar(Post p) {
//        Post p = getPost(idPostABorrar, listaPostGrupo);
//        postFacade.deletePostGrupo(postABorrar, usuario, grupo);
        if (muroPropio) {
//            usuario.getPostCollection().remove(postABorrar);
//            usuarioMuro.getPostCollection().remove(postABorrar);
//            grupo.getPostCollection().remove(postABorrar);
            usuario.getPostCollection().remove(p);
            usuarioMuro.getPostCollection().remove(p);
            grupo.getPostCollection().remove(p);
            listaPostGrupo.remove(p);
            postFacade.remove(p);
            usuarioFacade.edit(usuario);
            grupoFacade.edit(grupo);

        }
        postABorrar = null;
        return null;
    }

    // Metodos auxiliares
    private String getParameter(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    private Post getPost(BigDecimal idPost, Collection<Post> lista) {
        Post res = null;
        Iterator it = lista.iterator();
        boolean encontrado = false;
        while (it.hasNext() && !encontrado) {
            res = (Post) it.next();
            if (res.getIdPost().equals(idPost)) {
                encontrado = true;
            }
        }
        return res;
    }
    
    private int posicionPost(Post post, List<Post> lista){
        int i=0;
        Iterator<Post> it = lista.iterator();
        boolean encontrado = false;
        while(it.hasNext() && !encontrado){
            Post p = it.next();
            if(p.getIdPost().equals(post.getIdPost())){
                encontrado = true;
            }else{
                i++;
            }
        }
        return i;
    }

}
