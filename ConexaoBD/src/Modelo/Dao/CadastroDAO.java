/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.me.concexaobd.ConexaoBD;

/**
 *
 * @author Marcio Damazio
 */
public class CadastroDAO {

    public String msg;
    public boolean check = false;
    public boolean check2 = false;
    public boolean check3 = false;

    public boolean checkUser(String Usuario, String ID) {

        if (Usuario != null || ID != null) {
            Connection con = null;
            try {
                con = ConexaoBD.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {

                stmt = con.prepareStatement("SELECT * FROM TABUsers WHERE Usuario = ? or id_de_funcionario = ?");
                stmt.setString(1, Usuario);
                stmt.setString(2, ID);

                rs = stmt.executeQuery();

                if (rs.next()) {
                    check2 = true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConexaoBD.closeConnection(con, stmt, rs);
            }

        } else {
            check2 = false;
        }

        return check2;
    }

    public boolean checkFunc(String id) {

        Connection con = null;
        try {
            con = ConexaoBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement("SELECT * FROM TABFUNCIONARIO WHERE id_de_funcionario = ?");
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check3 = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return check3;
    }

    public String cadastro(String NOME_COMPLETO, String Usuario, String ID_DE_FUNCIONARIO, String Senha, String confSenha) {

        Connection con = null;
        try {
            con = ConexaoBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if (Senha.equals(confSenha)) {
            try {

                stmt = con.prepareStatement("INSERT INTO EMPRESA.TABUsers " + "(NOME_COMPLETO , USUARIO, ID_DE_FUNCIONARIO, SENHA) " + "VALUES (?, ?, ?, ?)");
                stmt.setString(1, NOME_COMPLETO);
                stmt.setString(2, Usuario);
                stmt.setString(3, ID_DE_FUNCIONARIO);
                stmt.setString(4, Senha);

                stmt.executeUpdate();
                this.msg = "Usuário cadastrado com sucesso!";
                this.check = true;

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConexaoBD.closeConnection(con, stmt, rs);
            }
        } else {
            this.msg = "Senhas não correspondem!";
        }

        return msg;
    }
}
