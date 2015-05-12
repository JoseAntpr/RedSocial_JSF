/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joseantpr
 */
@Entity
@Table(name = "PRIVADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privado.findAll", query = "SELECT p FROM Privado p"),
    @NamedQuery(name = "Privado.findByIdPrivado", query = "SELECT p FROM Privado p WHERE p.idPrivado = :idPrivado"),
    @NamedQuery(name = "Privado.findByDescripcion", query = "SELECT p FROM Privado p WHERE p.descripcion = :descripcion")})
public class Privado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRIVADO")
    private BigDecimal idPrivado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_USUARIO_DESTINO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuarioDestino;
    @JoinColumn(name = "ID_USUARIO_ORIGEN", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuarioOrigen;
    @OneToMany(mappedBy = "idPrivado")
    private Collection<Notificacion> notificacionCollection;

    public Privado() {
    }

    public Privado(BigDecimal idPrivado) {
        this.idPrivado = idPrivado;
    }

    public Privado(BigDecimal idPrivado, String descripcion) {
        this.idPrivado = idPrivado;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdPrivado() {
        return idPrivado;
    }

    public void setIdPrivado(BigDecimal idPrivado) {
        this.idPrivado = idPrivado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public void setIdUsuarioDestino(Usuario idUsuarioDestino) {
        this.idUsuarioDestino = idUsuarioDestino;
    }

    public Usuario getIdUsuarioOrigen() {
        return idUsuarioOrigen;
    }

    public void setIdUsuarioOrigen(Usuario idUsuarioOrigen) {
        this.idUsuarioOrigen = idUsuarioOrigen;
    }

    @XmlTransient
    public Collection<Notificacion> getNotificacionCollection() {
        return notificacionCollection;
    }

    public void setNotificacionCollection(Collection<Notificacion> notificacionCollection) {
        this.notificacionCollection = notificacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivado != null ? idPrivado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privado)) {
            return false;
        }
        Privado other = (Privado) object;
        if ((this.idPrivado == null && other.idPrivado != null) || (this.idPrivado != null && !this.idPrivado.equals(other.idPrivado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eajsf.entity.Privado[ idPrivado=" + idPrivado + " ]";
    }
    
}
