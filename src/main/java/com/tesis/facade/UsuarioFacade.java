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
                = "FROM Usuario u WHERE u.usuario = ?1 "
                + "and u.clave = ?2";

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
}
