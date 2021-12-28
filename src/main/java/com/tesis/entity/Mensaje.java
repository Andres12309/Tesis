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
 * @author Personal
 */
@Entity
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m")
    , @NamedQuery(name = "Mensaje.findById", query = "SELECT m FROM Mensaje m WHERE m.id = :id")
    , @NamedQuery(name = "Mensaje.findByIdUserSender", query = "SELECT m FROM Mensaje m WHERE m.idUserSender = :idUserSender")
    , @NamedQuery(name = "Mensaje.findByIdUserReceiver", query = "SELECT m FROM Mensaje m WHERE m.idUserReceiver = :idUserReceiver")
    , @NamedQuery(name = "Mensaje.findByMensaje", query = "SELECT m FROM Mensaje m WHERE m.mensaje = :mensaje")
    , @NamedQuery(name = "Mensaje.findByEstado", query = "SELECT m FROM Mensaje m WHERE m.estado = :estado")
    , @NamedQuery(name = "Mensaje.findByTipo", query = "SELECT m FROM Mensaje m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Mensaje.findByUrlImagen", query = "SELECT m FROM Mensaje m WHERE m.urlImagen = :urlImagen")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUserSender")
    private int idUserSender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUserReceiver")
    private int idUserReceiver;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mensaje")
    private int mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "urlImagen")
    private int urlImagen;
    @JoinColumn(name = "idChat", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chat idChat;

    public Mensaje() {
    }

    public Mensaje(Integer id) {
        this.id = id;
    }

    public Mensaje(Integer id, int idUserSender, int idUserReceiver, int mensaje, int estado, int tipo, int urlImagen) {
        this.id = id;
        this.idUserSender = idUserSender;
        this.idUserReceiver = idUserReceiver;
        this.mensaje = mensaje;
        this.estado = estado;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUserSender() {
        return idUserSender;
    }

    public void setIdUserSender(int idUserSender) {
        this.idUserSender = idUserSender;
    }

    public int getIdUserReceiver() {
        return idUserReceiver;
    }

    public void setIdUserReceiver(int idUserReceiver) {
        this.idUserReceiver = idUserReceiver;
    }

    public int getMensaje() {
        return mensaje;
    }

    public void setMensaje(int mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(int urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Chat getIdChat() {
        return idChat;
    }

    public void setIdChat(Chat idChat) {
        this.idChat = idChat;
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
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tesis.entity.Mensaje[ id=" + id + " ]";
    }
    
}
