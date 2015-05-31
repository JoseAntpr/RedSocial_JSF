/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.ejb;

import eajsf.entity.Grupo;
import eajsf.entity.Post;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jesus
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "RedSocial_JSF-ejbPU")
    private EntityManager em;

    @EJB
    private GrupoFacade grupoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> findPostIdUsuarioOrder (BigDecimal id_usuario) {
        Query q;
        List<Post> listaPost;        
        
        q = em.createQuery("SELECT p FROM Post p WHERE p.idUsuarioP.idUsuario=:ID ORDER BY p.idPost DESC");
        
        q.setParameter("ID", id_usuario);
        listaPost = q.getResultList();
        return listaPost;
        
    }
    
    public List<Post> findPostIdUsuarioSeguidoresOrder (BigDecimal id_usuario) {
        Query q;
        List<Post> post;        
        
        q = em.createQuery("SELECT p FROM Post p, Usuario u ,IN (u.usuarioCollection) us WHERE u.idUsuario=:ID AND p.idUsuarioP.idUsuario=us.idUsuario ORDER BY p.idPost DESC");
        
        q.setParameter("ID", id_usuario);
        post = (List) q.getResultList();
        return post;
        
    }
    
    public void deletePost(BigDecimal id_post){
        Query q;
        
        q=em.createQuery("DELETE FROM Post p WHERE p.idPost=:ID");
        q.setParameter("ID", id_post);
        
        q.executeUpdate();
    }
    
    public void deletePostGrupo(Post post, Usuario usuario, Grupo grupo){
        // Busco el Post en la BD
//        Post post = find(id_post);
        
        // Borro el post en el usuario sesion
        usuario.getPostCollection().remove(post);
        
        // Actualizo el usuario en la BD
        usuarioFacade.edit(usuario);
        
        // Borro el post en el grupo al que pertenece
//        Grupo grupo = grupoFacade.find(post.getIdGrupoP().getIdGrupo());
        grupo.getPostCollection().remove(post);
        
        
        // Actualizo el grupo en la BD
        grupoFacade.edit(grupo);
        
        // Borro el post de la BD
        remove(post);
        
    }
    
    public List getListaPostGrupo(BigDecimal idGrupo){
        return em.createQuery("SELECT p FROM Post p WHERE p.idGrupoP.idGrupo = :ID ORDER BY p.idPost DESC")
                .setParameter("ID", idGrupo)
                .getResultList();
        
    }
    
}
