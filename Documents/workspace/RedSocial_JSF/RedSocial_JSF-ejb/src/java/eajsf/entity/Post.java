/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "POST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByIdPost", query = "SELECT p FROM Post p WHERE p.idPost = :idPost"),
    @NamedQuery(name = "Post.findByDescripcion", query = "SELECT p FROM Post p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Post.findByFecha", query = "SELECT p FROM Post p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Post.findByImagen", query = "SELECT p FROM Post p WHERE p.imagen = :imagen")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_POST")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "secuencia_post")
    @SequenceGenerator(name="secuencia_post", sequenceName = "POST_SEQ", allocationSize=1)
    private BigDecimal idPost;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 256)
    @Column(name = "IMAGEN")
    private String imagen;
    @JoinColumn(name = "ID_GRUPO_P", referencedColumnName = "ID_GRUPO")
    @ManyToOne
    private Grupo idGrupoP;
    @JoinColumn(name = "ID_USUARIO_P", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuarioP;

    public Post() {
    }

    public Post(BigDecimal idPost) {
        this.idPost = idPost;
    }

    public Post(BigDecimal idPost, String descripcion, Date fecha) {
        this.idPost = idPost;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public BigDecimal getIdPost() {
        return idPost;
    }

    public void setIdPost(BigDecimal idPost) {
        this.idPost = idPost;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Grupo getIdGrupoP() {
        return idGrupoP;
    }

    public void setIdGrupoP(Grupo idGrupoP) {
        this.idGrupoP = idGrupoP;
    }

    public Usuario getIdUsuarioP() {
        return idUsuarioP;
    }

    public void setIdUsuarioP(Usuario idUsuarioP) {
        this.idUsuarioP = idUsuarioP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPost != null ? idPost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.idPost == null && other.idPost != null) || (this.idPost != null && !this.idPost.equals(other.idPost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eajsf.entity.Post[ idPost=" + idPost + " ]";
    }
    
}
