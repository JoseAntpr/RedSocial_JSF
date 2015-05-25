/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.ejb;

import eajsf.entity.Grupo;
import eajsf.entity.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jesus
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {
    @PersistenceContext(unitName = "RedSocial_JSF-ejbPU")
    private EntityManager em;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
     public Grupo crearGrupo(Usuario administrador, String nombre, String privacidad, String descripcion) {
        Grupo grupo = new Grupo();
        BigInteger idAdmin = new BigInteger(administrador.getIdUsuario().toString());
        grupo.setIdAdministradorG(idAdmin);
        grupo.setNombre(nombre);
        grupo.setImagen("imagen");

        if (privacidad.toUpperCase().equals("PUBLICO")) {
            grupo.setPrivacidad(BigInteger.ZERO);
        } else {
            grupo.setPrivacidad(BigInteger.ONE);
        }
        create(grupo);
        return grupo;
    }

    public void eliminarGrupo(Grupo grupoEliminar, Usuario usuario) {
        
        // Eliminamos el grupo de cada miembro y lo actualizamos en BD
        for (Usuario u : grupoEliminar.getUsuarioCollection()){
            if (u.getIdUsuario().equals(usuario.getIdUsuario())){
//                u.getGrupoCollection().remove(grupoEliminar);
//                usuario = u;
                usuario.getGrupoCollection().remove(grupoEliminar);
                usuarioFacade.edit(usuario);
            }
            u.getGrupoCollection().remove(grupoEliminar);
            usuarioFacade.edit(u);
        }
        
        // Eliminamos el grupo en la BD
        remove(grupoEliminar);

    }

    public List buscarGrupos(String datos) {
        List<Grupo> lista = null;
        lista = em.createNamedQuery("Grupo.findByNombreBuscar").setParameter("datos", "%" + datos + "%").getResultList();

        return lista;
    }

    public void abandonarGrupo(Grupo grupo, Usuario usuario) {
//        BigDecimal idGrupoAbandonar = new BigDecimal(idGrupo);
//        Grupo grupo = find(idGrupoAbandonar);

        Integer size = grupo.getUsuarioCollection1().size();
        if (size.compareTo(1) == 0) {
            // Eliminio el grupo del usuario
            usuario.getGrupoCollection1().remove(grupo);
            // Actualizo el usuario en BD
            usuarioFacade.edit(usuario);
            // Borro el grupo de la BD (Se ha quedado sin miembros)
            remove(grupo);
        } else if (size >= 2) {
            // Es el administrador
//            if (usuario.getIdUsuario().equals(grupo.getIdAdministradorG().getIdUsuario())) {
            if (usuario.getIdUsuario().equals(new BigDecimal(grupo.getIdAdministradorG().toString()))) {
                

                // Cambio el administrador del grupo
                List<Usuario> listaUsuarios = (List) grupo.getUsuarioCollection();

//                Usuario nuevoAdmin = null;
                BigInteger nuevoAdmin = null;
                if(usuario.getIdUsuario().equals(listaUsuarios.get(0).getIdUsuario())){
                    nuevoAdmin = new BigInteger(listaUsuarios.get(1).getIdUsuario().intValue()+"");
//                    nuevoAdmin = listaUsuarios.get(1);
                }else{
                    nuevoAdmin = new BigInteger(listaUsuarios.get(0).getIdUsuario().intValue()+"");
//                    nuevoAdmin = listaUsuarios.get(0);
                }
                grupo.setIdAdministradorG(nuevoAdmin);
                
                // Eliminio el grupo del usuario
                usuario.getGrupoCollection1().remove(grupo);

                // Elimino el usuario del grupo
                grupo.getUsuarioCollection1().remove(usuario);

                // Actualizo el grupo en BD
                edit(grupo);

                // Actualizo el usuario en BD
                usuarioFacade.edit(usuario);
            } else { // No es el administrador
                // Elimino el usuario del grupo
                grupo.getUsuarioCollection1().remove(usuario);

                // Eliminio el grupo del usuario
                usuario.getGrupoCollection1().remove(grupo);

                // Actualizo el grupo en BD
                edit(grupo);

                // Actualizo el usuario en BD
                usuarioFacade.edit(usuario);
            }
        }

    }

    public void unirseGrupo(String idGrupo, Usuario usuario) {
        BigDecimal idGrupoUnirse = new BigDecimal(idGrupo);
        Grupo grupo = find(idGrupoUnirse);

        // Elimino el usuario del grupo
        grupo.getUsuarioCollection().add(usuario);

        // Eliminio el grupo del usuario
        usuario.getGrupoCollection().add(grupo);

        // Actualizo el grupo en BD
        edit(grupo);

        // Actualizo el usuario en BD
        usuarioFacade.edit(usuario);
    }
    
}
