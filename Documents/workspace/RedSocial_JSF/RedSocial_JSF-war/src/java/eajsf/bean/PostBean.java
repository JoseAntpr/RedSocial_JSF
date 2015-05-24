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
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Jesus
 */
@ManagedBean
@RequestScoped
public class PostBean {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PostFacade postFacade;

    private String text = null;
    private Part img = null;
    private String imgContent = null;

    /**
     * Creates a new instance of PostBean
     */
    public PostBean() {
    }

    public PostFacade getPostFacade() {
        return postFacade;
    }

    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }
    
    

    public void validarImagen(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if ((file.getSize() / (1024)) > 4096) {
            msgs.add(new FacesMessage("Sólo se permiten imágenes con tamaño inferior a 4Mb"));
        }
        if (!"image/jpeg".equals(file.getContentType())) {
            msgs.add(new FacesMessage("Solo se admiten imágenes .jpg"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public String addPost() throws IOException {
        Usuario usuario = loginBean.getUsuario();
        //Lista Post de un Usuario
        List<Post> listaPost = (List) usuario.getPostCollection();

//        if (null != img) {
//            try {
//                InputStream is = img.getInputStream();
//                imgContent = new Scanner(is).useDelimiter("\\A").next();
//            } catch (IOException ex) {
//            }
//        }
        
//        img.write("B:\\data\\"+getFilename(img));
//        imgContent="B:\\data\\"+getFilename(img);

        //Añadir post con facade persist a base de datos
        Post p = new Post();
        p.setIdUsuarioP(usuario);
        p.setDescripcion(this.text); //request.getParameter("postContenido")
        p.setFecha(new Date());
//        p.setImagen(this.imgContent);

        //Actualizamos la lista de Post del Usuario
        listaPost.add(p);

        //Añadimos el post a la BD
        postFacade.create(p);

        // Actualizamos la relacion USUARIO-POST (MURO)
        usuarioFacade.edit(usuario);

        return "muro";
    }
    
    
     private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public String borrarPost(BigDecimal idPost) {

        postFacade.deletePost(idPost);

        return "muro";
    }

    public String redirectAddPost() {
        return "postAdd";
    }
}
