/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joseantpr
 */
@Entity
@Table(name = "NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByIdNotificacion", query = "SELECT n FROM Notificacion n WHERE n.idNotificacion = :idNotificacion")})
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NOTIFICACION")
    private BigDecimal idNotificacion;
    @JoinColumn(name = "ID_COMENTARIO", referencedColumnName = "ID_COMENTARIO")
    @ManyToOne
    private Comentario idComentario;
    @JoinColumn(name = "ID_PRIVADO", referencedColumnName = "ID_PRIVADO")
    @ManyToOne
    private Privado idPrivado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Notificacion() {
    }

    public Notificacion(BigDecimal idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public BigDecimal getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(BigDecimal idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Comentario getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Comentario idComentario) {
        this.idComentario = idComentario;
    }

    public Privado getIdPrivado() {
        return idPrivado;
    }

    public void setIdPrivado(Privado idPrivado) {
        this.idPrivado = idPrivado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eajsf.entity.Notificacion[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
