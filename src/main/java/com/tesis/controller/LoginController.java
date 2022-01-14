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

    public void iniciarSesion() {
        String redirect = null;
        try {
            Conexion con = new Conexion(false);
            UsuarioFacade usuariofacade = new UsuarioFacade(con);
            usuario = usuariofacade.iniciarSesion(usuario);
            
            FacesContext context = FacesContext.getCurrentInstance();

            if (usuario != null) {
                context.getExternalContext().getSessionMap().put("loginController", usuario);
                context.getExternalContext().redirect(urlIndex);
                con.closeConnection();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Usuario Ingresado es incorrecto");
                FacesContext.getCurrentInstance().addMessage(null, message);
                con.closeConnection();
            }
            con.closeConnection();
        } catch (Exception e) {
            //
        }
    }
    
    public void VerificarSesion(){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("loginController");
            
            if(us == null){
                context.getExternalContext().redirect(urlLogin);
            }
        }catch(Exception e){
            //
        }
    }

}
