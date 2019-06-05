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
