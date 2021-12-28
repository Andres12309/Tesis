/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c")
    , @NamedQuery(name = "Chat.findById", query = "SELECT c FROM Chat c WHERE c.id = :id")
    , @NamedQuery(name = "Chat.findByIdUser1", query = "SELECT c FROM Chat c WHERE c.idUser1 = :idUser1")
    , @NamedQuery(name = "Chat.findByIdUser2", query = "SELECT c FROM Chat c WHERE c.idUser2 = :idUser2")
    , @NamedQuery(name = "Chat.findByNumeroMensajes", query = "SELECT c FROM Chat c WHERE c.numeroMensajes = :numeroMensajes")})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser1")
    private int idUser1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser2")
    private int idUser2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroMensajes")
    private int numeroMensajes;
    @JoinColumn(name = "idPost", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Post idPost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idChat")
    private List<Mensaje> mensajeList;

    public Chat() {
    }

    public Chat(Integer id) {
        this.id = id;
    }

    public Chat(Integer id, int idUser1, int idUser2, int numeroMensajes) {
        this.id = id;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.numeroMensajes = numeroMensajes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public int getNumeroMensajes() {
        return numeroMensajes;
    }

    public void setNumeroMensajes(int numeroMensajes) {
        this.numeroMensajes = numeroMensajes;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
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
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tesis.entity.Chat[ id=" + id + " ]";
    }
    
}
