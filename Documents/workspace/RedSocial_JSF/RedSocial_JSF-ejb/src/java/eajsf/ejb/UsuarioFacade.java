/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.ejb;

import eajsf.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Joseantpr
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "RedSocial_JSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    /**
     * 
     * @param email
     * @param password
     * @return Usuario
     * 
     * Azahar: Método para buscar usuarios en el login.
     * Devuelve el usuario si lo encuentra, y si no devuelve null.
     */
    public Usuario login(String email, String password){
        
        Usuario user = null;
        
        //Creamos una consulta que busque usuarios por email y contraseña.
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:email AND u.password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        
        try{
            user = (Usuario) query.getSingleResult();  
        }catch ( Exception ex){}
                    
        
        return user;
    } 
    
}
