/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.facade;

import com.tesis.entity.Post;
import java.sql.PreparedStatement;

/**
 *
 * @author Personal
 */
public class PostFacade {

    private Conexion con;

    public PostFacade(Conexion con) {
        this.con = con;
    }

    public Boolean crearPost(Post post) {

        String query
                = "INSERT INTO post ( \n"
                + "     idUser,titulo,descripcion,urlImage, \n"
                + "     estado) \n"
                + "values ( \n"
                + "     ?,?, \n"
                + "     ?,?,? )\n";

        PreparedStatement st = null;
        try {
            post.setEstado("A");

            st = con.getConnection().prepareStatement(query);
            st.setInt(1, post.getIdUser());
            st.setString(2, post.getTitulo());
            st.setString(3, post.getDescripcion());
            st.setString(4, post.getUrlImagen());
            st.setString(5, post.getEstado());

            st.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(this.getClass().toString() + ".crearPost " + e.getMessage());
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                System.out.println(this.getClass().toString() + ".crearPost " + e.getMessage());
            }
        }
    }
}
