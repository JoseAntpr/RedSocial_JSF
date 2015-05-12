/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joseantpr
 */
@Entity
@Table(name = "BLOQUEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bloqueo.findAll", query = "SELECT b FROM Bloqueo b"),
    @NamedQuery(name = "Bloqueo.findByIdBloqueo", query = "SELECT b FROM Bloqueo b WHERE b.bloqueoPK.idBloqueo = :idBloqueo"),
    @NamedQuery(name = "Bloqueo.findByIdUsuario", query = "SELECT b FROM Bloqueo b WHERE b.bloqueoPK.idUsuario = :idUsuario")})
public class Bloqueo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BloqueoPK bloqueoPK;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Bloqueo() {
    }

    public Bloqueo(BloqueoPK bloqueoPK) {
        this.bloqueoPK = bloqueoPK;
    }

    public Bloqueo(BigInteger idBloqueo, BigInteger idUsuario) {
        this.bloqueoPK = new BloqueoPK(idBloqueo, idUsuario);
    }

    public BloqueoPK getBloqueoPK() {
        return bloqueoPK;
    }

    public void setBloqueoPK(BloqueoPK bloqueoPK) {
        this.bloqueoPK = bloqueoPK;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloqueoPK != null ? bloqueoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloqueo)) {
            return false;
        }
        Bloqueo other = (Bloqueo) object;
        if ((this.bloqueoPK == null && other.bloqueoPK != null) || (this.bloqueoPK != null && !this.bloqueoPK.equals(other.bloqueoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eajsf.entity.Bloqueo[ bloqueoPK=" + bloqueoPK + " ]";
    }
    
}
