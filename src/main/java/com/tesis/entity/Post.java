/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import org.primefaces.model.StreamedContent;

public class Post implements Serializable {

    private Integer id;
    private Integer idUser;
    private String titulo;
    private String descripcion;
    private byte[] urlImagen;
    private String imagen;
    private String estado;
    
    private BufferedImage imgBuf;
    private Image img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(byte[] urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BufferedImage getImgBuf() {
        return imgBuf;
    }

    public void setImgBuf(BufferedImage imgBuf) {
        this.imgBuf = imgBuf;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
