/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "detPost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetPost.findAll", query = "SELECT d FROM DetPost d")
    , @NamedQuery(name = "DetPost.findById", query = "SELECT d FROM DetPost d WHERE d.id = :id")
    , @NamedQuery(name = "DetPost.findByTipo", query = "SELECT d FROM DetPost d WHERE d.tipo = :tipo")
    , @NamedQuery(name = "DetPost.findByEstado", query = "SELECT d FROM DetPost d WHERE d.estado = :estado")})
public class DetPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idPost", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Post idPost;

    public DetPost() {
    }

    public DetPost(Integer id) {
        this.id = id;
    }

    public DetPost(Integer id, String tipo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetPost)) {
            return false;
        }
        DetPost other = (DetPost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tesis.entity.DetPost[ id=" + id + " ]";
    }
    
}
