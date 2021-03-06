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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    @EJB
    private GrupoFacade grupoFacade;

    @EJB
    private PostFacade postFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    private String nombre;
    private String apellidos;
    private String direccion;
    private String localidad;
    private String provincia;
    private String pais;
    private String email;
    private String descripcion;
    private String password;
    private String password2;
    private String passError=null;

    private Usuario usuario;
    private Usuario usuarioMuro;
    private Usuario usuarioEditar;
    private String error = null;
    
    private List<Post> listaPostUsuario;
    private List<Post> listaPostSigues;
    private List<Usuario> listaUsuarios;
     private List<Usuario> listaUsuariosAdmin;
    private List<Grupo> listaGrupos;

    private String idListaUsuarios;
    
    
    
    @PostConstruct
    public void Init(){         
         listaUsuariosAdmin = usuarioFacade.findAll();
         listaGrupos = grupoFacade.findAll();
    }

    public List<Usuario> getListaUsuariosAdmin() {
        return listaUsuariosAdmin;
    }

    public void setListaUsuariosAdmin(List<Usuario> listaUsuariosAdmin) {
        this.listaUsuariosAdmin = listaUsuariosAdmin;
    }

    public String getPassError() {
        return passError;
    }

    public void setPassError(String passError) {
        this.passError = passError;
    }
    
    
    
     public Boolean esAdministrador() {
          return usuarioFacade.esAdmin(usuario);        
          
      }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getIdListaUsuarios() {
        return idListaUsuarios;
    }

    public void setIdListaUsuarios(String idListaUsuarios) {
        this.idListaUsuarios = idListaUsuarios;
    }
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public GrupoFacade getGrupoFacade() {
        return grupoFacade;
    }

    public void setGrupoFacade(GrupoFacade grupoFacade) {
        this.grupoFacade = grupoFacade;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }
    
    public String comprobarUsuario() {

        Usuario usr = usuarioFacade.login(this.email, this.password);
        String ruta = null;

        if (usr != null) {
            if (!"t".equals(usr.getBloqueado())){
                error="";
                ruta = "muro";
                this.usuario = usr;
                this.usuarioMuro = usr;
                listaPostUsuario = postFacade.findPostIdUsuarioOrder(usuario.getIdUsuario());
                listaPostSigues = postFacade.findPostIdUsuarioSeguidoresOrder(usuario.getIdUsuario());
            }else{
                error="El usuario con el que intenta acceder esta bloqueado.";
            }
        } else {
            error = "Nombre de usuario o contraseña incorrectos, vuelve a intentarlo porfavor";
            ruta = "login";
        }
        return ruta;
    }

    public String registrarUsuario() {
        String ruta = null;
        Usuario usr = usuarioFacade.buscarEmail(email);

        if (usr == null) {
            error="";
            usr = usuarioFacade.nuevoUser(this.nombre, this.apellidos, this.direccion, this.localidad, this.provincia, this.pais, this.email, this.password);
            this.usuario = usr;
            this.usuarioMuro = usr;
            listaPostUsuario = postFacade.findPostIdUsuarioOrder(usuario.getIdUsuario());
            listaPostSigues = postFacade.findPostIdUsuarioSeguidoresOrder(usuario.getIdUsuario());
            ruta = "muro";
        } else {
            error = "El email ya esta registrado en nuestra red social.";
            ruta = "login";
        }

        return ruta;

    }

    public Usuario buscarID(BigDecimal id) {
        return (Usuario) usuarioFacade.find(id);
    }

    public boolean sigues(Usuario u, Usuario u2) {
        return u.siguesUsuario(u2);
    }

    public PostFacade getPostFacade() {
        return postFacade;
    }

    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    public List<Post> getListaPostUsuario() {
        return listaPostUsuario;
    }

    public void setListaPostUsuario(List<Post> listaPostUsuario) {
        this.listaPostUsuario = listaPostUsuario;
    }

    public List<Post> getListaPostSigues() {
        return listaPostSigues;
    }

    public void setListaPostSigues(List<Post> listaPostSigues) {
        this.listaPostSigues = listaPostSigues;
    }
    
    

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }

    public String botonEditarPerfil() {
        usuarioEditar = usuarioMuro;
        return "editarUsuario.xhtml";
    }

    public String botonCambiarPass() {
        usuarioEditar = usuarioMuro;    
        error="";
        passError=null;
        return "editarPassword.xhtml";       
    }

     public String botonAdministrar() {       
        return "administrar.xhtml";
       
    }
    
    public String modificarUsuario() {

        usuarioFacade.editarUsuario(usuarioEditar, usuarioEditar.getNombre(), usuarioEditar.getApellidos(), usuarioEditar.getDireccion(), usuarioEditar.getLocalidad(), usuarioEditar.getProvincia(), usuarioEditar.getPais(), usuarioEditar.getEmail(), usuarioEditar.getDescripcion());
        return "muro.xhtml";
    }

    public String modificarPassword() {
        
        String ruta = null;
        
        if (usuarioEditar.getPassword().equals(password2)){
            error="";
            usuarioFacade.editarPass(usuarioEditar, usuarioEditar.getPassword());
            ruta = "muro.xhtml";            
        }else{
            error="Las contraseñas no coinciden. Intentelo de nuevo.";
            ruta="editarPassword.xhtml"; 
            passError="teEquivocasteWey";
        }
        
        return ruta;
    }
     public String cerrarSesion() {
        nombre = null;
        apellidos = null;
        direccion = null;
        localidad = null;
        provincia = null;
        pais = null;
        email = null;
        descripcion = null;
        password = null;
        usuario = null;
        usuarioMuro = null;
        usuarioEditar = null;
        listaPostUsuario = null;
        listaUsuarios = null;
        idListaUsuarios = null;
        password2=null;
        error="";
        passError=null;
        
        return "login.xhtml";
    }
   
    public LoginBean() {
    }

}
