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
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
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
    private StreamedContent fileView;

    //manejo de imagenes
    private String ruta;
    private StreamedContent chart;

    @PostConstruct
    public void init() {
        post = new Post();
        Conexion con = new Conexion(false);
//        UsuarioFacade usuariofacade = new UsuarioFacade(con);
//        usuario = usuariofacade.iniciarSesion(usuario);
        obtenerPosts();

//        listPost = postFacade.obtenerPostxUser(getSesion().getUsuario().getId().toString());
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

    public StreamedContent getFileView() {
        return fileView;
    }

    public void setFileView(StreamedContent fileView) {
        this.fileView = fileView;
    }

    public void inicio() {
//        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(HelpPController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
    }

    public void crearPost() {
        FileInputStream fileInput = null;
        Conexion con = new Conexion(false);
        try {
            PostFacade postfacade = new PostFacade(con);

//            File fil = new File(ruta);
//            fileInput = new FileInputStream(file.getContent());
            post.setIdUser(getSesion().getId());

            if (!postfacade.crearPost(post, file)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Error al momento de publicar");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Publicacion realizada con exitó");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("");
            }

            con.closeConnection();

        } catch (Exception e) {
            con.closeConnection();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Se tuvo problemas para crear post. Log " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(this.getClass().toString() + ".crearPost " + e.getMessage());
        }
    }

    public void obtenerPosts() {
        Conexion con = new Conexion(false);

        try {
            PostFacade postFacade = new PostFacade(con);
            listPost = postFacade.obtenerPosts();

//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se encontraron las siguientes publicaciones");
//                FacesContext.getCurrentInstance().addMessage(null, message);
            con.closeConnection();

        } catch (Exception e) {
            con.closeConnection();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Se tuvo problemas para cargar post. Log " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(this.getClass().toString() + ".obtenerPost " + e.getMessage());
        }
    }

    public void obtenerPostUserLog() {
        Conexion con = new Conexion(false);

        try {
            PostFacade postFacade = new PostFacade(con);
            if (getSesion().getId() != 0 || getSesion().getId() != null) {
                listPost = postFacade.obtenerPostxUser(getSesion().getId());

//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se encontraron las siguientes publicaciones");
//                FacesContext.getCurrentInstance().addMessage(null, message);
            }

            con.closeConnection();

        } catch (Exception e) {
            con.closeConnection();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Se tuvo problemas para cargar post. Log " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(this.getClass().toString() + ".obtenerPost " + e.getMessage());
        }
    }

    public StreamedContent getChart() {
        return chart;
    }

    public byte[] getChartAsByteArray() throws IOException {

        return listPost.get(0).getUrlImagen();
    }

    public Usuario getSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        return us;
    }
}
