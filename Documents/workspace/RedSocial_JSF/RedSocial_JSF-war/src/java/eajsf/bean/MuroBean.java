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

    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    @EJB
    private PostFacade postFacade;

    
    
    @PostConstruct
    public void init() {
        Usuario usuario =usuarioFacade.find(loginBean.getIdUsuario());

        Usuario usuarioMuro = usuarioFacade.find(loginBean.getIdUsuarioMuro());

        List<Post> listaPost = null;
        List<Post> postSigues = null;

        String idUsuarioMuroGetString = request.getParameter("usuarioMuroGet");

        String mensaje = null;

        if (idUsuarioMuroGetString != null) {
            BigDecimal idUsuarioMuroGet = new BigDecimal(idUsuarioMuroGetString);
            Usuario usuarioMuroGet = usuarioFacade.find(idUsuarioMuroGet);
            if (!usuarioMuro.getIdUsuario().equals(idUsuarioMuroGet)) {
                sesion.setAttribute("usuarioMuro", usuarioMuroGet);
                usuarioMuro = usuarioMuroGet;
            }
        } else {
            sesion.setAttribute("usuarioMuro", usuario);
            usuarioMuro = usuario;
        }

        if (usuarioMuro.getIdUsuario().equals(usuario.getIdUsuario())) {
            listaPost = postFacade.findPostIdUsuarioOrder(usuario.getIdUsuario());
            List<Usuario> listaSigues = (List) usuario.getUsuarioCollection();

            postSigues = postFacade.findPostIdUsuarioSeguidoresOrder(usuario.getIdUsuario());

        } else {

            if (usuario.siguesUsuario(usuarioMuro).equals("si")) {

                listaPost = postFacade.findPostIdUsuarioOrder(usuarioMuro.getIdUsuario());

            } else {
                listaPost = postFacade.findPostIdUsuarioOrder(usuario.getIdUsuario());
                sesion.setAttribute("usuarioMuro", usuario);
                usuarioMuro = usuario;
//                response.encodeURL("/MuroServlet"); //QUITAR DE LA URL LA ID DEL usuarioMuro
                mensaje = "Error, no puedes ver el muro de un usuario que no sigues.";
            }
        }

        request.setAttribute("mensajeErrorMuroOtro", mensaje);
//        request.setAttribute("usuarioMuro", usuarioMuro);
        request.setAttribute("listaPostSigues", postSigues);
        request.setAttribute("listaPost", listaPost); //Para mandar listaPost a muro.jsp
    }

    /**
     * Creates a new instance of MuroBean
     */
    public MuroBean() {
    }

}
