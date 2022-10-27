/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.facade;

import com.tesis.entity.Post;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author Personal
 */
public class PostFacade {

    private Conexion con;

    public PostFacade(Conexion con) {
        this.con = con;
    }

    public Boolean crearPost(Post post, UploadedFile file) {
        String ruta = "C:\\Users\\JAMP\\Desktop\\TESIS_FINAL\\Tesis\\src\\main\\webapp\\images";
        String query
                = "INSERT INTO post ( \n"
                + "     idUser,titulo,descripcion,urlImagen, \n"
                + "     imagen, estado) \n"
                + "values ( \n"
                + "     ?,?, \n"
                + "     ?,?,?,? )\n";

        PreparedStatement st = null;
        try {
            post.setEstado("A");

            st = con.getConnection().prepareStatement(query);
            st.setInt(1, post.getIdUser());
            st.setString(2, post.getTitulo());
            st.setString(3, post.getDescripcion());
            st.setBinaryStream(4, file.getInputStream());
            st.setString(5, file.getFileName());
            st.setString(6, post.getEstado());

            st.executeUpdate();
            
            Path path = Paths.get(ruta,file.getFileName());
            Files.write(path, IOUtils.toByteArray( file.getInputStream()));

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

    public List<Post> obtenerPostxUser(int idUser) {

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
                + "     p.idUser = ? \n"
                + "     and p.estado = ? ";

        PreparedStatement st = null;
        ResultSet rs = null;
        Blob blob = null;

        try {

            List<Post> listPost = new ArrayList<>();
            Post post = null;
            st = con.getConnection().prepareStatement(query);
            st.setInt(1, idUser);
            st.setString(2, "A");
            rs = st.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    post = new Post();

                    post.setId(rs.getInt("id"));
                    post.setIdUser(rs.getInt("idUser"));
                    post.setTitulo(rs.getString("titulo"));
                    post.setDescripcion(rs.getString("descripcion"));
                    post.setUrlImagen(rs.getBytes("urlImagen"));
                    blob = rs.getBlob("urlImagen");

                    byte[] dataBlob = blob.getBytes(1, (int) blob.length());
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(dataBlob));
                    } catch (IOException ex) {
                        Logger.getLogger(PostFacade.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    post.setImg(img);
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
    
    public List<Post> obtenerPosts() {

        String query
                = "SELECT \n"
                + "     p.id, \n"
                + "     p.idUser, \n"
                + "     p.titulo, \n"
                + "     p.descripcion, \n"
                + "     p.urlImagen, \n"
                + "     p.imagen, \n"
                + "     p.estado \n"
                + "FROM \n"
                + "     post p \n"
                + "where \n"
                + "     p.estado = ? ";

        PreparedStatement st = null;
        ResultSet rs = null;
        Blob blob = null;

        try {
            List<Post> listPost = new ArrayList<>();
            Post post = null;
            st = con.getConnection().prepareStatement(query);
            st.setString(1, "A");
            rs = st.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    post = new Post();
                    
                    post.setId(rs.getInt("id"));
                    post.setIdUser(rs.getInt("idUser"));
                    post.setTitulo(rs.getString("titulo"));
                    post.setDescripcion(rs.getString("descripcion"));
                    post.setUrlImagen(rs.getBytes("urlImagen"));
                    post.setImagen(rs.getString("imagen"));
                    post.setEstado(rs.getString("estado"));
                    listPost.add(post);
                }
            }

            return listPost;
        } catch (Exception e) {
            System.out.println(this.getClass().toString() + ".obtenerPosts " + e.getMessage());
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
                System.out.println(this.getClass().toString() + ".obtenerPosts " + e.getMessage());
            }
        }
    }
}
