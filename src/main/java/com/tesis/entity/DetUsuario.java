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
@Table(name = "detUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetUsuario.findAll", query = "SELECT d FROM DetUsuario d")
    , @NamedQuery(name = "DetUsuario.findById", query = "SELECT d FROM DetUsuario d WHERE d.id = :id")
    , @NamedQuery(name = "DetUsuario.findByRol", query = "SELECT d FROM DetUsuario d WHERE d.rol = :rol")
    , @NamedQuery(name = "DetUsuario.findByEstado", query = "SELECT d FROM DetUsuario d WHERE d.estado = :estado")})
public class DetUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "rol")
    private String rol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "IdUser", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUser;

    public DetUsuario() {
    }

    public DetUsuario(Integer id) {
        this.id = id;
    }

    public DetUsuario(Integer id, String rol, String estado) {
        this.id = id;
        this.rol = rol;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof DetUsuario)) {
            return false;
        }
        DetUsuario other = (DetUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tesis.entity.DetUsuario[ id=" + id + " ]";
    }
    
}
