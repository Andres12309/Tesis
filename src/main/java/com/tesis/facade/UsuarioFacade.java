/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.facade;

import com.tesis.entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioFacade {

    private Conexion con;

    public UsuarioFacade(Conexion con) {
        this.con = con;
    }

    public Usuario iniciarSesion(Usuario usuario) {

        String query 
                = "SELECT \n"
                + "     u.nombres, \n"
                + "     u.apellidos, \n"
                + "     u.usuario, \n"
                + "     u.clave \n"
                + "FROM \n"
                + "     usuario u \n"
                + "where \n"
                + "     u.usuario = ? \n"
                + "     and u.clave = ? \n";

        PreparedStatement st = null;
        ResultSet rs = null;
        try {

            Usuario us = null;
            st = con.getConnection().prepareStatement(query);
            st.setString(1, usuario.getUsuario());
            st.setString(2, usuario.getClave());
            rs = st.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    us = new Usuario();
                    us.setNombres(rs.getString("nombres"));
                    us.setApellidos(rs.getString("apellidos"));
                    us.setUsuario(rs.getString("usuario"));
                    us.setClave(rs.getString("clave"));
                }
            }

            return us;
        } catch (Exception e) {
            System.out.println(this.getClass().toString() + ".iniciarSesion " + e.getMessage());
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
                System.out.println(this.getClass().toString() + ".iniciarSesion " + e.getMessage());
            }
        }
    }
    
    public Boolean registrarUsuario(Usuario usuario) {

        String query 
                = "INSERT INTO usuario ( \n"
                + "     nombres,apellidos,usuario,clave, \n"
                + "     confirmarClave,correo,cedula,telefono,provincia,canton,estado,rol) \n"
                + "values ( \n"
                + "     ?,?,?,?,?,?,?,?,?, \n"
                + "     ?,?,? )\n";

        PreparedStatement st = null;
        try {
            usuario.setConfirmarClave(usuario.getClave());
            usuario.setEstado("A");
            
            st = con.getConnection().prepareStatement(query);
            st.setString(1, usuario.getNombres());
            st.setString(2, usuario.getApellidos());
            st.setString(3, usuario.getUsuario());
            st.setString(4, usuario.getClave());
            st.setString(5, usuario.getConfirmarClave());
            st.setString(6, usuario.getCorreo());
            st.setString(7, usuario.getCedula());
            st.setString(8, usuario.getTelefono());
            st.setString(9, usuario.getProvincia());
            st.setString(10, usuario.getCanton());
            st.setString(11, usuario.getEstado());
            st.setString(12, usuario.getRol());
            st.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(this.getClass().toString() + ".registrarUsuario " + e.getMessage());
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                System.out.println(this.getClass().toString() + ".registrarUsuario " + e.getMessage());
            }
        }
    }
}
