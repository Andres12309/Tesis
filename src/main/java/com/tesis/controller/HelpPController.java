/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.controller;

import com.tesis.entity.Usuario;
import com.tesis.facade.Conexion;
import com.tesis.facade.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Personal
 */
@ManagedBean(name = "helpPController")
@ViewScoped
public class HelpPController implements Serializable {

    private Usuario usuario;

    @PostConstruct
    public void init() {
//        Conexion con = new Conexion(false);
//        UsuarioFacade usuariofacade = new UsuarioFacade(con);
//        usuario = usuariofacade.iniciarSesion(usuario);
//        con.closeConnection();
    }

    public String iniciarSesion() {
        
        return "/login?faces-redirect=true";
    }
}
