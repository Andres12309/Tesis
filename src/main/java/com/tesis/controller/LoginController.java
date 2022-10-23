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
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable {

    private Usuario usuario;
    String urlIndex;
    String urlLogin;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        urlIndex = "index.xhtml";
        urlLogin = "login.xhtml";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        try {
            Conexion con = new Conexion(false);
            UsuarioFacade usuariofacade = new UsuarioFacade(con);
            usuario = usuariofacade.iniciarSesion(usuario);

            FacesContext context = FacesContext.getCurrentInstance();

            if (usuario != null) {
                context.getExternalContext().redirect(urlIndex);
                context.getExternalContext().getSessionMap().put("usuario", usuario);
                con.closeConnection();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Usuario Ingresado es incorrecto");
                context.addMessage(null, message);
                con.closeConnection();
            }
            
            //int id = VerificarSesion().getId();
            con.closeConnection();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Se tuvo problemas para iniciar sesion. Log " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(this.getClass().toString() + ".iniciarSesion " + e.getMessage());
        }
        
        return "";
    }

    public Usuario getVerificarSesion() {
        Usuario us = null;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

            if (us == null) {
                context.getExternalContext().redirect(urlLogin);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Se tuvo problemas para verificar la session. Log " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(this.getClass().toString() + ".VerificarSesion " + e.getMessage());
        }
        
        return us;
    }

}
