/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.controller;

import com.tesis.entity.Post;
import com.tesis.entity.Usuario;
import com.tesis.facade.Conexion;
import com.tesis.facade.PostFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Personal
 */
@ManagedBean(name = "helpPController")
@ViewScoped
public class HelpPController implements Serializable {

    private Usuario usuario;
    private List<Post> listPost;
    private Post post;
    private UploadedFile file;

    @PostConstruct
    public void init() {
        Conexion con = new Conexion(false);
//        UsuarioFacade usuariofacade = new UsuarioFacade(con);
//        usuario = usuariofacade.iniciarSesion(usuario);
        PostFacade postFacade = new PostFacade(con);
        LoginController loginController = new LoginController();

        listPost = postFacade.obtenerPostxUser(getSesion().getUsuario().getId().toString());
        con.closeConnection();
    }

    public List<Post> getListPost() {
        return listPost;
    }

    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void iniciarSesion() {
//        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(HelpPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrase() {
//        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registroUsuario.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(HelpPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearPost() {
        try {
            Conexion con = new Conexion(false);
            PostFacade postfacade = new PostFacade(con);

            if (!postfacade.crearPost(post)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Error al momento de registrar");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro completado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("");
            }

            con.closeConnection();

        } catch (Exception e) {
            //
        }
    }

    private LoginController getSesion() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        LoginController loginController = (LoginController) context.getSessionMap().get("loginController");
        
        return loginController;
    }
}
