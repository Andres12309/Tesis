/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.facade;

import com.tesis.entity.Post;
import com.tesis.entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Post> obtenerPostxUser(String idUser) {

        String query
                = "SELECT \n"
                + "     p.id, \n"
                + "     p.idUser, \n"
                + "     p.titulo, \n"
                + "     p.descripcion, \n"
                + "     p.urlImagen, \n"
                + "     p.estado \n"
                + "FROM \n"
                + "     post p \n"
                + "where \n"
                + "     p.usuario = ? \n"
                + "     and p.estado = A \n";

        PreparedStatement st = null;
        ResultSet rs = null;
        try {

            List<Post> listPost = new ArrayList<>();
            Post post = null;
            st = con.getConnection().prepareStatement(query);
            st.setString(1, idUser);
            rs = st.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    post = new Post();
                    post.setId(rs.getInt("id"));
                    post.setIdUser(rs.getInt("idUser"));
                    post.setTitulo(rs.getString("titulo"));
                    post.setDescripcion(rs.getString("descripcion"));
                    post.setUrlImagen(rs.getString("urlImage"));
                    post.setEstado(rs.getString("estado"));
                    listPost.add(post);
                }
            }

            return listPost;
        } catch (Exception e) {
            System.out.println(this.getClass().toString() + ".obtenerPostxUser " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                System.out.println(this.getClass().toString() + ".obtenerPostxUser " + e.getMessage());
            }
        }
    }
}
