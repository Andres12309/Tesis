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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Personal
 */
@ManagedBean(name = "registerController")
@ViewScoped
public class RegisterController implements Serializable {

    private Usuario usuario;
    private TreeNode tipoUsuario;
    String urlIndex;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        urlIndex = "index.xhtml";

        tipoUsuario = new DefaultTreeNode("tipoUsuario", null);
        TreeNode solicitante = new DefaultTreeNode("Solicitante", tipoUsuario);
        TreeNode colaborador = new DefaultTreeNode("Colaborador", tipoUsuario);

//        TreeNode solicitanteDef = new DefaultTreeNode("Node 0.0", solicitante);
//        TreeNode colaboradorDef = new DefaultTreeNode("Node 0.1", colaborador);
        solicitante.getChildren().
                add(new DefaultTreeNode("Este tipo de registro le permitira solicitar apoyo, tanto en medicamentos o atención medica, \n"
                        + "                                          esto sera atendido por los diferentes colaboradores dispuestos a ayudar."));
        colaborador.getChildren().add(new DefaultTreeNode("Este tipo de registro le permitira brindar su apoyo de corazón, \n"
                + "                                          a diferentes usuarios solicitantes que esten requiriendo atencion medica o medicamentos."));
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TreeNode getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TreeNode tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void registrarUsuario() {

        try {
            Conexion con = new Conexion(false);
            UsuarioFacade usuariofacade = new UsuarioFacade(con);

            if (!usuariofacade.registrarUsuario(usuario)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Error al momento de registrar");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro completado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect(urlIndex);
            }

            con.closeConnection();

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Se tuvo problemas para registrar usuario. Log " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(this.getClass().toString() + ".registrarUsuario " + e.getMessage());
        }

    }
}
