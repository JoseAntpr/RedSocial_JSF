/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Joseantpr
 */
@Embeddable
public class BloqueoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BLOQUEO")
    private BigInteger idBloqueo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private BigInteger idUsuario;

    public BloqueoPK() {
    }

    public BloqueoPK(BigInteger idBloqueo, BigInteger idUsuario) {
        this.idBloqueo = idBloqueo;
        this.idUsuario = idUsuario;
    }

    public BigInteger getIdBloqueo() {
        return idBloqueo;
    }

    public void setIdBloqueo(BigInteger idBloqueo) {
        this.idBloqueo = idBloqueo;
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBloqueo != null ? idBloqueo.hashCode() : 0);
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueoPK)) {
            return false;
        }
        BloqueoPK other = (BloqueoPK) object;
        if ((this.idBloqueo == null && other.idBloqueo != null) || (this.idBloqueo != null && !this.idBloqueo.equals(other.idBloqueo))) {
            return false;
        }
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eajsf.entity.BloqueoPK[ idBloqueo=" + idBloqueo + ", idUsuario=" + idUsuario + " ]";
    }
    
}
